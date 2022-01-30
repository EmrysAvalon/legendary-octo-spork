package com.example.dierenarts.controller;

import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
public class PetController {

    //Constructor to set example pets.
    public PetController() {
        Pet olivia = new Pet("Olivia", LocalDate.of(2020, 5, 29), "Tanja");
        Pet oliver = new Pet("Oliver", LocalDate.of(2020, 7, 22), "Tanja");
        Pet pim = new Pet("Pim", LocalDate.of(2020, 8, 19), "Tanja");

        repository.save(olivia);
        repository.save(oliver);
        repository.save(pim);
    }

    @Autowired
    private PetRepository repository;

    @GetMapping(value = "/pets")
    public ResponseEntity<Object> getPets() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/pets/{id}")
    public ResponseEntity<Object> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @DeleteMapping(value = "/pets/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/pets")
    public ResponseEntity<Object> addPet(@RequestBody Pet pet) {
        Pet newPet = repository.save(pet);
        Long newId = pet.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/pets/{id}")
    public ResponseEntity<Object> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        Pet existingPet = repository.findById(id).orElse(null);

        if (!pet.getName().isEmpty()) {
            existingPet.setName(pet.getName());
        }
        if (!pet.getOwner().isEmpty()) {
            existingPet.setOwner(pet.getOwner());
        }
        if (pet.getDateOfBirth() != null) {
            existingPet.setDateOfBirth(pet.getDateOfBirth());
        }
        repository.save(existingPet);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/pets/{id}")
    public ResponseEntity<Object> partialUpdatePet(@PathVariable Long id, @RequestBody Pet pet) {
        Pet existingPet = repository.findById(id).orElse(null);
        if (!pet.getName().isEmpty()) {
            existingPet.setName(pet.getName());
        }
        if (!pet.getOwner().isEmpty()) {
            existingPet.setOwner(pet.getOwner());
        }
        if (pet.getDateOfBirth() != null) {
            existingPet.setDateOfBirth(pet.getDateOfBirth());
        }
        repository.save(existingPet);
        return ResponseEntity.noContent().build();
    }

}
