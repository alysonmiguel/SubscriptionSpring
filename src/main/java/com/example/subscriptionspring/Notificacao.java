package com.example.subscriptionspring;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Notificacao {

    Gson gson = new Gson();

    @PostMapping("/subscribe")
    public void subscription(@RequestBody String json) {

        System.out.println("DEU CERTO" + json);





//        AtualizaSgeol atualizaSgeol = new AtualizaSgeol();
//        atualizaSgeol.atualizarDados(json);
    }

//    @GetMapping("/teste")
//    public String teste(){
//        return "subscribe";
//    }
}
