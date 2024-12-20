package com.example.car.parts;

import lombok.ToString;

@ToString
public class Accumulator {
    private String capacity;

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}