package com.example.microservicereciever.serializers;

import com.example.microservicereciever.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class PersonSerializer implements Serializer<Person> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Person data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
