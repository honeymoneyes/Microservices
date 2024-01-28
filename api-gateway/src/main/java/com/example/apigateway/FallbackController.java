package com.example.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/getServiceFallback")
    public Mono<String> addServiceFallBack() {
        return Mono.just("Add Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/addServiceFallback")
    public Mono<String> getServiceFallBack() {
        return Mono.just("Get Service is taking too long to respond or is down. Please try again later");
    }
}
