package com.example.microservicereciever.payload;

import com.example.microservicereciever.entity.Person;
import lombok.Builder;

import java.util.List;

@Builder
public record PersonResponse(
        List<Person> personList
) {
}
