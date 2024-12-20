package com.example.car.parts;

import lombok.ToString;

@ToString
public class Starter {
    private String model;

    public void setModel(String model) {
        this.model = model;
    }
}