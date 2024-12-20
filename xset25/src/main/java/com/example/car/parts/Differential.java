package com.example.car.parts;

import lombok.ToString;

@ToString
public class Differential {
    private String type;

    public void setType(String type) {
        this.type = type;
    }
}