package com.example.addservice.service;

import com.example.addservice.GreetingServiceGrpc;
import com.example.addservice.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request, StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        System.out.println(request);

        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello from add-service - " + request.getName()).build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
