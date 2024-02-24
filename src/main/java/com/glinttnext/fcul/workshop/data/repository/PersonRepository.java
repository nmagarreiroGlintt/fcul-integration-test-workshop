package com.glinttnext.fcul.workshop.data.repository;

import com.glinttnext.fcul.workshop.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
