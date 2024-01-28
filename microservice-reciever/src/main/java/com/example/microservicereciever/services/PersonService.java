package com.example.microservicereciever.services;

import com.example.microservicereciever.entity.Person;
import com.example.microservicereciever.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public Person get(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @KafkaListener(topics = "topic1", groupId = "1")
    public Person save(Person person) {
        System.out.println(person + "- saved!");
        return repository.save(person);
    }
}
