package com.example.subscriptionspring.model;

public class EntitieModel {

    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entitie{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
