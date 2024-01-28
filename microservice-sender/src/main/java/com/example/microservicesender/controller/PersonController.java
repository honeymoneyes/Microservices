package com.example.microservicesender.controller;

import com.example.microservicesender.payload.Person;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add-service")
@RequiredArgsConstructor
public class PersonController {

    private final KafkaTemplate<String, Person> kafkaTemplate;
    private static final String TOPIC_1 = "topic1";
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Person person) {
        kafkaTemplate.send(TOPIC_1, person);
        return ResponseEntity.status(HttpStatus.OK).body("Message sent!");
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
