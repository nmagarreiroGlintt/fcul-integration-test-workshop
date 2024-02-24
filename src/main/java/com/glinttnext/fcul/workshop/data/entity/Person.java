package com.glinttnext.fcul.workshop.data.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "PERSON")
@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "globalSequence", sequenceName = "Id_Sequence", allocationSize = 30)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "globalSequence")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    public Person() {
    }

    public Person(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    private void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final LocalDate createdDate) {
        this.createdDate = createdDate;
    }

}
