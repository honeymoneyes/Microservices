package com.example.addservice.controller;

import com.example.addservice.payload.Person;
import com.example.addservice.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add-service")
@RequiredArgsConstructor
public class PersonController {

    private final KafkaTemplate<String, Person> kafkaTemplate;
    @Autowired
    private ApiService apiService;
    private static final String TOPIC_1 = "topic1";

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Person person) {
        kafkaTemplate.send(TOPIC_1, person);
        return ResponseEntity.status(HttpStatus.OK).body("Message sent!");
    }

    @GetMapping("/people")
    public List<Person> getPeople() {
        return apiService.getAll();
    }
}
