package com.example.getservice.specifications;

import com.example.getservice.entity.Person;
import com.example.getservice.entity.Person_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecifications {
    public static Specification<Person> hasFields(Long id, String name) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(Person_.ID), id));
            }

            if (name != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(Person_.NAME), name));
            }

            return predicate;
        };
    }
}
