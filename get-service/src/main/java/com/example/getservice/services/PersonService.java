package com.example.getservice.services;

import com.example.getservice.specifications.PersonSpecifications;
import com.example.getservice.entity.Person;
import com.example.getservice.entity.Person_;
import com.example.getservice.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final EntityManager entityManager;
    private final PersonRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public Person get(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public List<Person> getAllByConditions(Long id, String name) {
        var specification = Specification
                .where(PersonSpecifications.hasFields(id, name));
        return repository.findAll(specification);
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
    public void save(Person person) {
        repository.save(Person.builder()
                .name(person.getName())
                .build());
    }

    public void saveSpringJdbc() {
        for (int i = 0; i < 1_000_000; i++) {
            repository.save(Person.builder()
                    .name("Save hibernate" + i)
                    .build());

            jdbcTemplate.update("INSERT INTO person (name) VALUES (?)", "Save JDBC" + i);
        }
    }

    public List<Person> getAll() {
        return repository.findAll();
    }
}
