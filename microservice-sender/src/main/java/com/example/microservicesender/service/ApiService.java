package com.example.microservicesender.service;

import com.example.microservicesender.payload.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "gateway-service")
public interface ApiService {
    @RequestMapping(value = "/get-service/people", method = RequestMethod.GET)
    List<Person> getAll();
}
