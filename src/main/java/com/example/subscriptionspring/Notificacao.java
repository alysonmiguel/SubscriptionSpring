package com.example.subscriptionspring;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Controller
public class Notificacao {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    String destination = "/topic/messages";
    JSONObject jsonObject;

    @PostMapping("/subscribe")
    public void subscription(@RequestBody String json) {

//        AtualizarSgeol atualizaSgeol = new AtualizarSgeol();
//        atualizaSgeol.atualizarDados(json);

        jsonObject  = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        jsonObject = jsonArray.getJSONObject(0);
        String type = jsonObject.getString("type");

        atualizarView(type);

    }

    public void atualizarView(String type){
        switch (type){
            case "door_001":

                String state = jsonObject.getJSONObject("state").getString("value");
                System.out.println(state);

                simpMessagingTemplate.convertAndSend(destination,
                        "A porta está " + state);
                break;
            case "bell_001":
                String dataCompleta = jsonObject.getJSONObject("TimeInstant").getString("value");
                TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dataCompleta);
                Instant i = Instant.from(ta);
                Date d = Date.from(i);
                System.out.println(d);
                simpMessagingTemplate.convertAndSend(destination,
                        "Última vez que o sino tocou " + d + "\n {\n\t\"@context\": [\"https://forge.etsi.org/gitlab/NGSI-LD/NGSI-LD/raw/master/coreContext/ngsi-ld-core-context.json\",\n\t\t\"https://github.com/JorgePereiraUFRN/SGEOL-LD/blob/master/ngsi-ld/education/student/Student_Context.jsonld\"\n\t],\n\t\"id\": \"urn:ngsi-ld:Bell:001\",\n\t\"type\": \"bell_001\",\n    \t\"location\":{\n      \"type\":\"GeoProperty\",\n      \"value\":{\n         \"coordinates\":[\n            \"-35.36417\",\n            \"-5.88444\"\n         ],\n         \"type\":\"Point\"\n      }\n   },\n   \"Ultimo_toque\":{\n       \"type\": \"property\",\n       \"value\": \""+ d +"\"\n   }\n}");
                break;
//            case "motion_001":
//                System.out.println("motion_001");
//                break;
//            case "lamp_001":
//                System.out.println("lamp_001");
//                break;
            default: break;
        }

    }
//    @GetMapping("/teste")
//    public String teste(){
//        return "subscribe";
//    }
}
