package com.example.restboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestBootApplication.class, args);

        System.out.println("spring is running");
    }

}
