// src/main/java/com/example/car/config/CarConfig.java
package com.example.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.car.*;
import com.example.car.parts.*;

@Configuration
public class CarConfig {

    @Bean
    public SparkPlug sparkPlug() {
        SparkPlug sparkPlug = new SparkPlug();
        sparkPlug.setType("Standard");
        return sparkPlug;
    }

    @Bean
    public Starter starter() {
        Starter starter = new Starter();
        starter.setModel("ST-100");
        return starter;
    }

    @Bean
    public Engine engine(Starter starter, SparkPlug sparkPlug) {
        Engine engine = new Engine();
        engine.setStarter(starter);
        engine.setSparkPlug(sparkPlug);
        return engine;
    }

    @Bean
    public Wheel wheel() {
        Wheel wheel = new Wheel();
        wheel.setSize("R17");
        return wheel;
    }

    @Bean
    public Hinge hinge() {
        Hinge hinge = new Hinge();
        hinge.setType("Ball");
        return hinge;
    }

    @Bean
    public Differential differential() {
        Differential differential = new Differential();
        differential.setType("Limited-slip");
        return differential;
    }

    @Bean
    public Suspension suspension(Hinge hinge, Differential differential) {
        Suspension suspension = new Suspension();
        suspension.setHinge(hinge);
        suspension.setDifferential(differential);
        return suspension;
    }

    @Bean
    public Accumulator accumulator() {
        Accumulator accumulator = new Accumulator();
        accumulator.setCapacity("60Ah");
        return accumulator;
    }

    @Bean
    public Car car(Wheel wheel, Engine engine, Accumulator accumulator, Suspension suspension) {
        Car car = new Car();
        car.setWheel(wheel);
        car.setEngine(engine);
        car.setAccumulator(accumulator);
        car.setSuspension(suspension);
        return car;
    }
}