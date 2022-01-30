package com.example.dierenarts.service;

import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public Iterable<Pet> getPets() {
        return repository.findAll();
    }

    public Pet getPet(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePet(Long id) {
        repository.deleteById(id);
    }

    public Long addPet(Pet pet) {
        Pet newPet = repository.save(pet);
        return newPet.getId();
    }

    public void partialUpdatePet(Long id, Pet pet) {
        Pet existingPet = repository.findById(id).orElse(null);

        if (!pet.getName().isEmpty()) {
            existingPet.setName(pet.getName());
        }
        if (!pet.getDateOfBirth().equals(null)) {
            existingPet.setDateOfBirth(pet.getDateOfBirth());
        }
        if (!pet.getOwner().isEmpty()) {
            existingPet.setOwner(pet.getOwner());
        }
        repository.save(existingPet);
    }
}
