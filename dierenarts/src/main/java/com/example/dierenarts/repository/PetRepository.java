package com.example.dierenarts.repository;

import com.example.dierenarts.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}