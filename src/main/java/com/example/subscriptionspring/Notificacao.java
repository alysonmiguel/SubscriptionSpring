package com.example.subscriptionspring;

import com.example.subscriptionspring.model.BellModel;
import com.example.subscriptionspring.model.SubscriptionModel;
import com.google.gson.Gson;
import kong.unirest.HttpRequest;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Notificacao {

    Gson gson = new Gson();

    @PostMapping("/subscribe")
    public void subscription(@RequestBody String json) {

        AtualizaSgeol atualizaSgeol = new AtualizaSgeol();
        atualizaSgeol.atualizarDados(json);

    }


}
