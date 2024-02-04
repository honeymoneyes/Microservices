package com.example.addservice;

import com.example.addservice.service.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AddServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AddServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Server server = ServerBuilder.forPort(8888)
                .addService(new GreetingServiceImpl())
                .build();

        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }
}
