package com.example.getservice.serializers;

import com.example.getservice.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class PersonDeserializer implements Deserializer<Person> {
    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Person deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Person.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
