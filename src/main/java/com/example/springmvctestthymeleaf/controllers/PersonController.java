package com.example.springmvctestthymeleaf.controllers;

import com.example.springmvctestthymeleaf.models.Person;
import com.example.springmvctestthymeleaf.services.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {
    private final PersonServiceImpl personServiceImpl;
    @GetMapping()
    public String people(@RequestParam(name = "fullName",required = false) String fullName, Model model){
        model.addAttribute("people",personServiceImpl.people(fullName));
        return "people/people";
    }
    @GetMapping("/new")
    public String newPerson(){
        return "people/personCreation";
    }
    @PostMapping()
    public String create(Person person){
        personServiceImpl.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}")
    public String personInfo(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("person",personServiceImpl.getPersonById(id));
        model.addAttribute("books",personServiceImpl.getPersonById(id).getBooks());
        return "people/personInfo";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        personServiceImpl.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String personEdit(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("person",personServiceImpl.getPersonById(id));
        return "people/personEditing";
    }
    @PatchMapping("/{id}")
    public String saveModifiedPerson(@ModelAttribute("person") Person person,@PathVariable(value = "id") Long id){
        personServiceImpl.update(id, person);
        return "redirect:/people";
    }
}
