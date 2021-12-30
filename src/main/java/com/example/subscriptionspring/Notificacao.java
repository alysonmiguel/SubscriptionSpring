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

        AtualizarSgeol atualizaSgeol = new AtualizarSgeol();
        atualizaSgeol.atualizarDados(json);

        jsonObject  = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        jsonObject = jsonArray.getJSONObject(0);

        String type = jsonObject.getString("type");
        String id = jsonObject.getString("id");

        atualizarView(type, id);

    }

    public void atualizarView(String type, String id){
        switch (type){
            case "door_001":
                simpMessagingTemplate.convertAndSend(destination, id);
                break;
            case "bell_001":
                simpMessagingTemplate.convertAndSend(destination, id);
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
