package com.example.springmvctestthymeleaf.services.impl;

import com.example.springmvctestthymeleaf.models.Person;
import com.example.springmvctestthymeleaf.repositories.PersonRepository;
import com.example.springmvctestthymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    @Override
    public List<Person> people(String fullName) {
        if(fullName!=null) return personRepository.findPersonByFullNameContaining(fullName);
        return personRepository.findAll();
    }
    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Person person) {
        personRepository.save(person);
    }
    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
    @Override
    public void update(Long id, Person person) {
        Person personToBeUpdated = getPersonById(id);
        personToBeUpdated.setFullName(person.getFullName());
        personToBeUpdated.setYearOfBirth(person.getYearOfBirth());
        personRepository.save(personToBeUpdated);
    }
}
