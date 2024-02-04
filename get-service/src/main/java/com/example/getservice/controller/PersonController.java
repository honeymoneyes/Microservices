package com.example.getservice.controller;

import com.example.getservice.entity.Person;
import com.example.getservice.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/get-service")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/people")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/test-criteria")
    public List<Person> getCriteriaAll() {
        return personService.getCriteriaAll();
    }

    @GetMapping("/test-criteria-by-name/{name}")
    public List<Person> getCriteriaAllByName(@PathVariable("name") String name) {
        return personService.getCriteriaAllByName(name);
    }

    @GetMapping("/test-criteria-order-by")
    public List<Person> getCriteriaAllByName() {
        return personService.getCriteriaAllOrderByName();
    }

    @GetMapping("/dynamic")
    public List<Person> getAllByParams(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name) {
        System.out.println(id);
        System.out.println(name);
        return personService.getAllByParams(id, name);
    }

    @GetMapping("/get/{personId}")
    public ResponseEntity<?> get(@PathVariable("personId") Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.get(personId));
    }
}