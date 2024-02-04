package com.example.getservice.payload;

import com.example.getservice.entity.Person;
import lombok.Builder;

import java.util.List;

@Builder
public record PersonResponse(
        List<Person> personList
) {
}
