package com.example.springmvctestthymeleaf.services;

import com.example.springmvctestthymeleaf.models.Book;

import java.util.List;

public interface BookService {
    List<Book> books(String name);
    void save(Book book);
    Book getBookById(Long id);

    void delete(Long id);

    void update(Long id,Book book);

    void updateReader(Long id,Book book);
    List<Book> pageOfBooks(int page,int itemsPerPage);

}
