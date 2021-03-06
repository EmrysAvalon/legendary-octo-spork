package com.example.dierenarts.repository;

import com.example.dierenarts.model.Owner;
import com.example.dierenarts.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Iterable<Pet> findAllByName(String name);
    Iterable<Pet> findAllByNameContainingIgnoreCase(String name);
    Iterable<Pet> findAllByOwner(Owner owner);
}
