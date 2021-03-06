package com.example.dierenarts.controller;

import com.example.dierenarts.dto.PetRequestDto;
import com.example.dierenarts.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping(value = "/pets")
    public ResponseEntity<Object> getPets(@RequestParam(name = "name", defaultValue = "") String name) {
        return ResponseEntity.ok(service.getPets(name));
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
    public ResponseEntity<Object> addPet(@Valid @RequestBody PetRequestDto petRequestDto) {
        Long newId = service.addPet(petRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/pets/{id}")
    public ResponseEntity<Object> partialUpdatePet(@PathVariable Long id, @RequestBody PetRequestDto pet) {
        service.partialUpdatePet(id, pet);
        return ResponseEntity.noContent().build();
    }

}
