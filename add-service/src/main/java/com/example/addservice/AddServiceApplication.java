package com.example.addservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AddServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddServiceApplication.class, args);
    }

}
