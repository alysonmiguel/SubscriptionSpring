package com.example.subscriptionspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Notificacao {

    @PostMapping("/subscribe")
    public void subscription(@RequestBody String json) {

        AutenticacaoSgeol autenticacao = new AutenticacaoSgeol();
        System.out.println(autenticacao.getTokens());

        System.out.println(json);

//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        System.out.println(now.format(formatter));

//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

//        System.out.println(now.format(formatter));
//        System.out.println(json.getData().get(0).getRing_info().getMetadata().getTimeInstant().setValue("dfd"));
//
//        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/sgeol-dm/v2/bell_001")
//                .header("application-token", "50d6f55d-3795-4dd3-8d7b-1e57f47f0aaa")
//                .header("user-token", "ee56904e-aa87-425d-93ee-32646b0c7a60")
//                .asJson();
//
//        System.out.println(response.getBody());
//
//        HttpResponse<String> teste = Unirest.put("http://localhost:8080/sgeol-dm/v2/bell_001")
//                .header("Content-Type", "application/json")
//                .header("application-token", appToken)
//                .header("user-token", userToken)
//                .body("{\n\t\"@context\": [\"https://forge.etsi.org/gitlab/NGSI-LD/NGSI-LD/raw/master/coreContext/ngsi-ld-core-context.json\",\n\t\t\"https://github.com/JorgePereiraUFRN/SGEOL-LD/blob/master/ngsi-ld/education/student/Student_Context.jsonld\"\n\t],\n\t\"id\": \"urn:ngsi-ld:layer:bell001\",\n\t\"type\": \"bell_001\",\n\t\"ultimo_toque\": {\n\t\t\"type\": \"Property\",\n\t\t\"value\": \"" + now.format(formatter) + "\"\n\t},\n\t\"location\": {\n\t\t\"type\": \"GeoProperty\",\n\t\t\"value\": {\n\t\t\t\"coordinates\": [\"-35.201842\", \"-5.832977\"],\n\t\t\t\"type\": \"Point\"\n\t\t}\n\t}\n}")
//                .asString();

    }


}