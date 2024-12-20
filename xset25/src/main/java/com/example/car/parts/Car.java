package com.example.car.parts;

import lombok.ToString;

@ToString
public class Car {
    private Wheel wheel;
    private Engine engine;
    private Accumulator accumulator;
    private Suspension suspension;

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setAccumulator(Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }
}