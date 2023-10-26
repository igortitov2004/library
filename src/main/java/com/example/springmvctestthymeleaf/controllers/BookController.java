package com.example.springmvctestthymeleaf.controllers;

import com.example.springmvctestthymeleaf.models.Book;
import com.example.springmvctestthymeleaf.models.Person;
import com.example.springmvctestthymeleaf.services.impl.BookServiceImpl;
import com.example.springmvctestthymeleaf.services.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookServiceImpl;
    private final PersonServiceImpl personServiceImpl;
    @GetMapping()
    public String books(@RequestParam(name = "name",required = false) String name, Model model){
        model.addAttribute("books",bookServiceImpl.books(name));
        return "books/books";
    }
    @GetMapping("/new")
    public String newBook(){
        return "books/bookCreation";
    }
    @PostMapping()
    public String create(Book book){
       bookServiceImpl.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String bookInfo(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("book",bookServiceImpl.getBookById(id));
        model.addAttribute("people",personServiceImpl.people(null));
        return "books/bookInfo";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        bookServiceImpl.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/newReader")
    public String newReader(@ModelAttribute("book") Book book,@PathVariable(value = "id") Long id){
        bookServiceImpl.updateReader(id,book);
        return "redirect:/books";
    }
}
