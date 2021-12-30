package com.example.subscriptionspring;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.TimeZone;

public class AtualizarSgeol {

    public void atualizarDados(String dados){
        AutenticacaoSgeol autenticacao = new AutenticacaoSgeol();

        JSONObject jsonObject = new JSONObject(dados);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        jsonObject = jsonArray.getJSONObject(0);
        String type = jsonObject.getString("type");

        switch (type){
            case "door_001":
                System.out.println("door_001");
                System.out.println(jsonObject);
                String state = jsonObject.getJSONObject("state").getString("value");
                System.out.println(state);
                Unirest.put("http://localhost:8080/sgeol-dm/v2/fazenda")
                        .header("Content-Type", "application/json")
                        .header("application-token", autenticacao.getAppTokens())
                        .header("user-token", autenticacao.getUserTokens())
                        .body("{\n\t\"@context\": [\"https://forge.etsi.org/gitlab/NGSI-LD/NGSI-LD/raw/master/coreContext/ngsi-ld-core-context.json\",\n\t\t\"https://github.com/JorgePereiraUFRN/SGEOL-LD/blob/master/ngsi-ld/education/student/Student_Context.jsonld\"\n\t],\n\t\"id\": \"urn:ngsi-ld:Door:001\",\n\t\"type\": \"door_001\",\n\t\"location\":{\n      \"type\":\"GeoProperty\",\n      \"value\":{\n         \"coordinates\":[\n            \"-35.36583\",\n            \"-5.88508\"\n         ],\n         \"type\":\"Point\"\n      }\n   },\n   \"status\":{\n       \"type\": \"property\",\n       \"value\": \""+ state +"\"\n   }\n}")
                        .asString();
                break;
            case "bell_001":
                System.out.println("bell_001");
                System.out.println(jsonObject);
//                String dataCompleta = jsonObject.getJSONObject("TimeInstant").getString("value");
//                TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dataCompleta);
//                Instant i = Instant.from(ta);
//                Date d = Date.from(i);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dataAtual = LocalDateTime.now();
                String dataAtualFormatada = dataAtual.format(formatter);


                Unirest.put("http://localhost:8080/sgeol-dm/v2/fazenda")
                        .header("Content-Type", "application/json")
                        .header("application-token", autenticacao.getAppTokens())
                        .header("user-token", autenticacao.getUserTokens())
                        .body("{\n\t\"@context\": [\"https://forge.etsi.org/gitlab/NGSI-LD/NGSI-LD/raw/master/coreContext/ngsi-ld-core-context.json\",\n\t\t\"https://github.com/JorgePereiraUFRN/SGEOL-LD/blob/master/ngsi-ld/education/student/Student_Context.jsonld\"\n\t],\n\t\"id\": \"urn:ngsi-ld:Bell:001\",\n\t\"type\": \"bell_001\",\n    \t\"location\":{\n      \"type\":\"GeoProperty\",\n      \"value\":{\n         \"coordinates\":[\n            \"-35.36417\",\n            \"-5.88444\"\n         ],\n         \"type\":\"Point\"\n      }\n   },\n   \"Ultimo_toque\":{\n       \"type\": \"property\",\n       \"value\": \""+ dataAtualFormatada +"\"\n   }\n}")
                        .asString();

                break;
            case "motion_001":
                System.out.println("motion_001");
                break;
            case "lamp_001":
                System.out.println("lamp_001");
                break;
            default: break;
        }

    }

}
