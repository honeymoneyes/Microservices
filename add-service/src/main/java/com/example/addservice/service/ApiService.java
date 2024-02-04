package com.example.addservice.service;

import com.example.addservice.payload.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "gateway-service")
// Нужно указывать название сервиса gateway ( spring-app-name )
public interface ApiService {
    @RequestMapping(value = "/get-service/people", method = RequestMethod.GET)
    List<Person> getAll();
}
