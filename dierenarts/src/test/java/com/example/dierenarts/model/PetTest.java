package com.example.dierenarts.model;

import com.example.dierenarts.service.OwnerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    Pet pet = new Pet();
    Owner owner = new Owner();

    @BeforeEach
    public void setPet(){
        pet.setName("Olivia");
        pet.setDateOfBirth(LocalDate.of(2020, 5, 29));
        owner.setName("Tanja");
        pet.setOwner(owner);
    }

    @Test
    void getID() {
    }

    @Test
    void getName() {
        assertEquals("Olivia", pet.getName());
    }

    @Test
    void getDateOfBirth() {
        assertEquals(LocalDate.of(2020,5,29), pet.getDateOfBirth());
    }

    @Test
    void getOwner() {
        assertEquals(owner, pet.getOwner());
    }
}