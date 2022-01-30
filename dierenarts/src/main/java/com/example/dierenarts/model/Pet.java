package com.example.dierenarts.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pets")
public class Pet {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String owner;

    //Empty constructor
    public Pet() {

    }
    //Constructor for easy creation of a pet.
    public Pet(String name, LocalDate dateOfBirth, String owner){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.owner = owner;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getOwner() {
        return owner;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

