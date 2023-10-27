package com.example.springmvctestthymeleaf.services.impl;

import com.example.springmvctestthymeleaf.models.Book;
import com.example.springmvctestthymeleaf.repositories.BookRepository;
import com.example.springmvctestthymeleaf.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<Book> books(String name) {
        if(name!=null) return bookRepository.findBookByNameContaining(name);
        return bookRepository.findAll();
    }
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public void update(Long id, Book book) {
        Book bookToBeUpdated = getBookById(id);
        bookToBeUpdated.setName(book.getName());
        bookToBeUpdated.setAuthor(book.getAuthor());
        bookToBeUpdated.setYear(book.getYear());
        bookRepository.save(bookToBeUpdated);
    }

    @Override
    public void updateReader(Long id, Book book) {
        Book bookToBeUpdated = getBookById(id);
        bookToBeUpdated.setPerson(book.getPerson());
        bookRepository.save(bookToBeUpdated);
    }
}
