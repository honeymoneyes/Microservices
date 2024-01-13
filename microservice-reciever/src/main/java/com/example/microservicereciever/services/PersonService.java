package com.example.microservicereciever.services;

import com.example.microservicereciever.entity.Person;
import com.example.microservicereciever.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    @KafkaListener(topics = "topic1", groupId = "1")
    public Person save(Person person) {
        System.out.println(person + "- saved!");
        return repository.save(person);
    }
}
