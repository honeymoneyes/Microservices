package com.example.microservicereciever.controller;

import com.example.microservicereciever.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get-service")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/get/{personId}")
    public ResponseEntity<?> get(@PathVariable("personId") Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.get(personId));
    }
}