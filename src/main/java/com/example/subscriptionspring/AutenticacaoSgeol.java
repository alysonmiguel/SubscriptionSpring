package com.example.subscriptionspring;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class AutenticacaoSgeol {

    private String appToken = null;
    private String userToken = null;
    public String getAppTokens() {
        HttpResponse<JsonNode> responseApp = Unirest.post("http://localhost:8080/sgeol-dm/security/auth/login/token/management")
                .header("Content-Type", "application/json")
                .body("{ \n  \"name\":\"app_sgeol_test@test.com\",\n  \"password\":\"1234\"\n}")
                .asJson();
        if (responseApp.getStatus() == 200) {
            appToken = responseApp.getBody().getObject().getString("X-Subject-Token");
        }
        return  appToken;
    }
    public String getUserTokens() {
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


        if (responseUser.getStatus() == 200) {
            userToken = responseUser.getBody().getObject().getString("access_token");
        }

        return userToken;
    }

}
