package com.example.dierenarts.controller;

import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.PetRepository;
import com.example.dierenarts.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
public class PetController {

// Example pets.
// Olivia 2020-05-29
// Oliver 2020-07-22
// Pim 2020-08-19


    @Autowired
    private PetRepository repository;
    private PetService service;

    @GetMapping(value = "/pets")
    public ResponseEntity<Object> getPets() {
        return ResponseEntity.ok(service.getPets());
    }

    @GetMapping(value = "/pets/{id}")
    public ResponseEntity<Object> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPet(id));
    }

    @DeleteMapping(value = "/pets/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable("id") Long id) {
        service.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/pets")
    public ResponseEntity<Object> addPet(@RequestBody Pet pet) {
        Long newId = service.addPet(pet);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/pets/{id}")
    public ResponseEntity<Object> partialUpdatePet(@PathVariable Long id, @RequestBody Pet pet) {
        service.partialUpdatePet(id, pet);
        return ResponseEntity.noContent().build();
    }

}
