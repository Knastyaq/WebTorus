// src/main/java/com/example/car/Main.java
package com.example.car;

import com.example.car.parts.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.car.config.CarConfig;

public class Main {
    public static void main(String[] args) {
        // 1. XML-based configuration
        ApplicationContext contextXml =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Car carXml = contextXml.getBean("car", Car.class);
        System.out.println("Car created using XML configuration:");
        System.out.println(carXml);

        // 2. Java-based configuration
        ApplicationContext contextJava =
                new AnnotationConfigApplicationContext(CarConfig.class);
        Car carJava = contextJava.getBean(Car.class);
        System.out.println("\nCar created using Java configuration:");
        System.out.println(carJava);

        // 3. Mixed configuration (XML + Annotations)
        ApplicationContext contextMixed =
                new ClassPathXmlApplicationContext("applicationContext-mixed.xml");
        Car carMixed = contextMixed.getBean("car", Car.class);
        System.out.println("\nCar created using mixed configuration:");
        System.out.println(carMixed);
    }
}