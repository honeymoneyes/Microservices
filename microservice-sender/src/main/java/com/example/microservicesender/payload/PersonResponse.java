package com.example.microservicesender.payload;

import lombok.Builder;

import java.util.List;

@Builder
public record PersonResponse(
        List<Person> personList
) {
}
