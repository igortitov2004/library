package com.example.springmvctestthymeleaf.repositories;

import com.example.springmvctestthymeleaf.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBookByNameContaining(String name);

    Book findBookByNameStartingWith(String name);
    @Override
    Page<Book> findAll(Pageable pageable);
}
