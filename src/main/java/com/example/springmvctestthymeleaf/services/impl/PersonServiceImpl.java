package com.example.springmvctestthymeleaf.services.impl;

import com.example.springmvctestthymeleaf.models.Book;
import com.example.springmvctestthymeleaf.models.Person;
import com.example.springmvctestthymeleaf.repositories.PersonRepository;
import com.example.springmvctestthymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    public Optional<Person> getPersonByFullName(String fullName){
        return personRepository.findPersonByFullNameContaining(fullName).stream().findAny();
    }
    public List<Book> isExpired(List<Book> books){
        for (Book book : books) {
            book.setExpired(TimeUnit.DAYS.convert(new Date().getTime() - book.getDateOfBookTake().getTime(), TimeUnit.MILLISECONDS) > 10);
        }
        return books;
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
