package com.example.springmvctestthymeleaf.util;

import com.example.springmvctestthymeleaf.models.Person;
import com.example.springmvctestthymeleaf.services.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator{
    private final PersonServiceImpl personServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personServiceImpl.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName","","Такой человек уже существует");
        }
    }
}
