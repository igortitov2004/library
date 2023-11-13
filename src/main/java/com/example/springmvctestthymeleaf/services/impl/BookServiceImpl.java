package com.example.springmvctestthymeleaf.services.impl;

import com.example.springmvctestthymeleaf.models.Book;
import com.example.springmvctestthymeleaf.repositories.BookRepository;
import com.example.springmvctestthymeleaf.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    @Override
    public List<Book> books(String name) {
        if(name!=null) return bookRepository.findBookByNameContaining(name);
        return bookRepository.findAll();
    }

    public Book book(String name){
        return bookRepository.findBookByNameStartingWith(name);
    }
    @Override
    public List<Book> pageOfBooks(int page,int itemsPerPage){
        return bookRepository.findAll(PageRequest.of(page,itemsPerPage).withSort(Sort.Direction.ASC, "year")).getContent();
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
        if (book.getPerson() != null) {
            bookToBeUpdated.setDateOfBookTake(new Date());
        } else {
            bookToBeUpdated.setDateOfBookTake(null);
        }
        bookToBeUpdated.setPerson(book.getPerson());
        bookRepository.save(bookToBeUpdated);
    }
}
