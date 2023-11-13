package com.example.springmvctestthymeleaf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "books")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_books")
    private Long id_books;
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @Column(name="date_of_book_take")
    @Temporal(TemporalType.DATE)
    private Date dateOfBookTake;

    public boolean isExpired(){
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
    @Transient
    private boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


}
