package com.example.dierenarts.controller;

import com.example.dierenarts.dto.OwnerRequestDto;
import com.example.dierenarts.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService service;

    @GetMapping(value = "/owners")
    public ResponseEntity<Object> getOwners(@RequestParam(name="name", defaultValue="") String name) {
        return ResponseEntity.ok(service.getOwner(name));
    }

    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<Object> getOwners(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOwner(id));
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<Object> deleteOwner(@PathVariable("id") Long id) {
        service.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/owners")
    public ResponseEntity<Object> addOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        Long newId = service.addOwner(ownerRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/owners/{id}")
    public ResponseEntity<Object> partialUpdateOwner(@PathVariable Long id, @RequestBody OwnerRequestDto owner) {
        service.partialUpdateOwner(id, owner);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/owners/{id}/pets")
    public ResponseEntity<Object> getOwnerPets(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOwnerPets(id));
    }

}
