package com.example.subscriptionspring;

import com.example.subscriptionspring.model.EntitieModel;
import com.example.subscriptionspring.model.SubscriptionModel;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class SubscribeOrion {

    Gson gson = new Gson();

    public void deleteSubscription() {

        HttpResponse<JsonNode> listSubscriptions = Unirest.get("http://localhost:1026/v2/subscriptions")
                .header("fiware-service", "openiot")
                .header("fiware-servicepath", "/")
                .asJson();

        for (int i = 0; i < listSubscriptions.getBody().getArray().length(); i++) {

            String json = listSubscriptions.getBody().getArray().get(i).toString();
            SubscriptionModel listSubs = gson.fromJson(json, SubscriptionModel.class);
            Unirest.delete("http://localhost:1026/v2/subscriptions/" + listSubs.getId())
                    .header("fiware-service", "openiot")
                    .header("fiware-servicepath", "/")
                    .asString();
        }
    }


    public void listEntitieSubscribeOrion() {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:1026/v2/entities")
                .header("fiware-service", "openiot")
                .header("fiware-servicepath", "/")
                .asJson();

        for (int i = 0; i < response.getBody().getArray().length(); i++) {

            String json = response.getBody().getArray().get(i).toString();
            EntitieModel entitie = gson.fromJson(json, EntitieModel.class);
            HttpResponse<String> sub = Unirest.post("http://localhost:1026/v2/subscriptions/")
                    .header("fiware-service", "openiot")
                    .header("fiware-servicepath", "/")
                    .header("Content-Type", "application/json")
                    .body("{\n  \"description\": \"Subscription store:001\",\n  \"subject\": {\n    \"entities\": [\n      {\n        \"id\": \"" + entitie.getId() + "\",\n        \"type\": \"" + entitie.getType() + "\"\n      }\n    ]\n  },\n  \"notification\": {\n    \"http\": {\n      \"url\": \"http://192.168.1.72:8084/subscribe\"\n    }\n  },\n  \"expires\": \"2026-04-05T14:00:00.00Z\",\n  \"throttling\": 5\n}")
                    .asString();

            System.out.println("Entities : " + entitie.toString());
        }

    }

}
