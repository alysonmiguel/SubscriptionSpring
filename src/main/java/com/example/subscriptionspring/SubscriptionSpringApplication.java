package com.example.subscriptionspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubscriptionSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionSpringApplication.class, args);

        SubscribeOrion subscriptionOrion = new SubscribeOrion();
        subscriptionOrion.deleteSubscription();
        subscriptionOrion.listEntitieSubscribeOrion();

    }
}
