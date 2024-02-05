package com.example.getservice.services;

import com.example.getservice.entity.Person;
import com.example.getservice.entity.Person_;
import com.example.getservice.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final EntityManager entityManager;
    private final PersonRepository repository;

    public Person get(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public List<Person> getCriteriaAll() {

        var session = entityManager.unwrap(Session.class);

        var criteriaBuilder = session.getCriteriaBuilder();

        var criteria = criteriaBuilder.createQuery(Person.class);

        var person = criteria.from(Person.class);

        criteria.select(person);

        return session.createQuery(criteria).list();

    }

    public List<Person> getCriteriaAllByName(String name) {

        var session = entityManager.unwrap(Session.class);

        var criteriaBuilder = session.getCriteriaBuilder();

        var criteria = criteriaBuilder.createQuery(Person.class);

        var person = criteria.from(Person.class);

        criteria.select(person)
                .where(criteriaBuilder.equal(person.get(Person_.NAME), name));

        return session.createQuery(criteria).list();

    }

    public List<Person> getCriteriaAllOrderByName() {

        var session = entityManager.unwrap(Session.class);

        var criteriaBuilder = session.getCriteriaBuilder();

        var criteria = criteriaBuilder.createQuery(Person.class);

        var person = criteria.from(Person.class);

        criteria.select(person).orderBy(criteriaBuilder.asc(person.get(Person_.NAME)));

        return session.createQuery(criteria).list();

    }

    public List<Person> getAllByParams(Long id, String name) {

        var session = entityManager.unwrap(Session.class);

        var criteriaBuilder = session.getCriteriaBuilder();

        var criteria = criteriaBuilder.createQuery(Person.class);

        var predicate = criteriaBuilder.conjunction();

        var person = criteria.from(Person.class);

        if (id == null && name == null) {
            return Collections.emptyList();
        }

        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(person.get(Person_.ID), id));
        }

        if (name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(person.get(Person_.NAME), name));
        }

        criteria.select(person).where(predicate);

        return session.createQuery(criteria).list();
    }

    @KafkaListener(topics = "topic1", groupId = "1")
    public void save(String person) throws IOException {
        System.out.println(person + "- saved!");
        JsonNode node = new ObjectMapper().readTree(person);

        var id = node.get("id").asLong();
        var name = node.get("name").asText();
        repository.save(Person.builder()
                .id(id)
                .name(name)
                .build());
    }

    public List<Person> getAll() {
        return repository.findAll();
    }
}
