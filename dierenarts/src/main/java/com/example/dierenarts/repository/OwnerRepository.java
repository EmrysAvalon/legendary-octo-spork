package com.example.dierenarts.repository;

import com.example.dierenarts.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Iterable<Owner> findAllByName(String name);
    Iterable<Owner> findAllByNameContainingIgnoreCase(String name);

}
