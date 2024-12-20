package com.example.car.parts;

import lombok.ToString;

@ToString
public class Suspension {
    private Hinge hinge;
    private Differential differential;

    public void setHinge(Hinge hinge) {
        this.hinge = hinge;
    }

    public void setDifferential(Differential differential) {
        this.differential = differential;
    }
}
