package com.example.car.parts;

import lombok.ToString;

@ToString
public class Wheel {
    private String size;

    public void setSize(String size) {
        this.size = size;
    }
}