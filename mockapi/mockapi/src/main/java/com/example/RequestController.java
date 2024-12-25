package com.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/app/v1")
public class RequestController {

    @GetMapping("/getRequest")
    public ResponseEntity<String> getRequest(@RequestParam int id, @RequestParam String name) {
        if (id <= 10) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("InternalServerError: id must be greater than 10");
        }
        if (name.length() <= 5) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("InternalServerError: name must be longer than 5 characters");
        }

        // Задержка
        try {
            Thread.sleep((id > 10 && id < 50) ? 1000 : 500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Чтение ответа из файла
        String responseBody;
        try {
            responseBody = new String(Files.readAllBytes(Paths.get("src/main/resources/getAnswer.txt")))
                    .replace("{name}", name);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("InternalServerError: Unable to read response file");
        }

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/postRequest")
    public ResponseEntity<String> postRequest(@RequestBody PostRequestBody body) {
        if (body.getName() == null || body.getName().isEmpty() ||
                body.getSurname() == null || body.getSurname().isEmpty() ||
                body.getAge() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("InternalServerError: name, surname, and age must not be empty");
        }

        // Чтение ответа из файла
        String responseBody;
        try {
            responseBody = new String(Files.readAllBytes(Paths.get("src/main/resources/postAnswer.txt")))
                    .replace("{name}", body.getName())
                    .replace("{surname}", body.getSurname())
                    .replace("{age}", String.valueOf(body.getAge()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("InternalServerError: Unable to read response file");
        }

        return ResponseEntity.ok(responseBody);
    }

    // Измененный класс RequestBody
    public static class PostRequestBody {
        private String name;
        private String surname;
        private Integer age;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}