package com.example.getservice;

import com.example.addservice.GreetingServiceGrpc;
import com.example.addservice.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GetServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8888").usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder().setName("Alex").build();

        var response = stub.greeting(request);

        System.out.println(response);
    }
}
