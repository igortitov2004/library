package com.example.springmvctestthymeleaf.services;

import com.example.springmvctestthymeleaf.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> people(String fullName);
    Person getPersonById(Long id);
    void save(Person person);
    void delete(Long id);
    void update(Long id,Person person);
}
