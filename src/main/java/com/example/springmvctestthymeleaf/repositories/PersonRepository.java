package com.example.springmvctestthymeleaf.repositories;

import com.example.springmvctestthymeleaf.models.Person;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findPersonByFullNameContaining(String fullName);

}
