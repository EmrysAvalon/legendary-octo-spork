package com.example.dierenarts.model;

import java.time.LocalDate;
import java.util.List;

public class Pet {

    //attributes
    Long ID;
    String name;
    LocalDate dateOfBirth;
    String owner;

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
    public Long getID() {
        return ID;
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

