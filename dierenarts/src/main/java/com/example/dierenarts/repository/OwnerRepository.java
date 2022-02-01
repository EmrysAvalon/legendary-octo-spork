package com.example.dierenarts.repository;

import com.example.dierenarts.model.Owner;
import com.example.dierenarts.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Iterable<Owner> findAllByName(String name);
    Iterable<Owner> findAllByNameContainingIgnoreCase(String name);

}
