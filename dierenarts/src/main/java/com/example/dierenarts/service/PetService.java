package com.example.dierenarts.service;

import com.example.dierenarts.dto.PetRequestDto;
import com.example.dierenarts.exception.RecordNotFoundException;
import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;
    private OwnerService ownerService;

    public Iterable<Pet> getPets(String name) {
        if (name.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findAllByNameContainingIgnoreCase(name);
        }
    }

    public Iterable<Pet> getPetsByOwner(Long ownerId) {
        return repository.findAllByOwner(ownerService.getOwner(ownerId));
    }

    public Pet getPet(Long id) {
        Optional<Pet> optionalPet = repository.findById(id);

        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new RecordNotFoundException("There is no pet with this id.");
        }
    }

    public void deletePet(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("There is no pet with this id.");
        }
    }

    public Long addPet(PetRequestDto petRequestDto) {
        Pet pet = new Pet();
        pet.setName(petRequestDto.getName());
        pet.setDateOfBirth(petRequestDto.getDateOfBirth());
        pet.setOwner(ownerService.getOwner(petRequestDto.getOwnerId()));
        Pet newPet = repository.save(pet);
        return newPet.getId();
    }

    public void partialUpdatePet(Long id, Pet pet) {
        Optional<Pet> optionalPet = repository.findById(id);

        if (optionalPet.isPresent()) {
            Pet existingPet = repository.findById(id).orElse(null);
            if (pet.getName() != null && !pet.getName().isEmpty()) {
                existingPet.setName(pet.getName());
            }
            if (pet.getDateOfBirth() != null && !pet.getName().isEmpty()) {
                existingPet.setDateOfBirth(pet.getDateOfBirth());
            }
            if (pet.getOwner() != null && !pet.getName().isEmpty()) {
                existingPet.setOwner(pet.getOwner());
            }
            repository.save(existingPet);
        } else {
            throw new RecordNotFoundException("There is no pet with this id.");
        }
    }

}
