package com.example.microservicesender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceSenderApplication.class, args);
    }

}
