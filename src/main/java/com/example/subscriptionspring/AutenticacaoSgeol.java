package com.example.subscriptionspring;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class AutenticacaoSgeol {

    public String getTokens(){
        HttpResponse<JsonNode> responseApp = Unirest.post("http://localhost:8080/sgeol-dm/security/auth/login/token/management")
                .header("Content-Type", "application/json")
                .body("{ \n  \"name\":\"app_sgeol_test@test.com\",\n  \"password\":\"1234\"\n}")
                .asJson();

        String appToken = null;
        if (responseApp.getStatus() == 200) {
            appToken = responseApp.getBody().getObject().getString("X-Subject-Token");
        }

        HttpResponse<JsonNode> responseUser = null;
        if (appToken != null) {
            responseUser = Unirest.post("http://localhost:8080/sgeol-dm/security/auth/login/user")
                    .header("Authorization", "ZTJiYjc5NDEtYTA1Zi00ZWI3LThkNDItOTMwZjY3OGFlZmFhOmE0YTQ3NWM5LTU3NGQtNDBiYy1hZDM1LTU4ZDUwOTNiOThkNK==")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("password", "1234")
                    .field("application-token", appToken)
                    .field("username", "gerente_sgeol_test@test.com")
                    .asJson();
        }

        String userToken = null;
        if (responseUser.getStatus() == 200) {
            userToken = responseUser.getBody().getObject().getString("access_token");
        }

//        System.out.println("App Token : " + appToken);
//        System.out.println("User Token : " + userToken);

        return "App Token : " + appToken + " \n User Token : " + userToken ;
    }

}
