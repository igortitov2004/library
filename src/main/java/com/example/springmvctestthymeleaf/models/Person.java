package com.example.springmvctestthymeleaf.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "people")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persons")
    private Long id_persons;
    @Column(name = "full_name")
    @Pattern(regexp = "([А-ЯЁ][а-яё]+[\\-\\s]?){3,}", message = "Некорректный ввод Ф.И.О.")
    private String fullName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;



    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Book> books;

}
