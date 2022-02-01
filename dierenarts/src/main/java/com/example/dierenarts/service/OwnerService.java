package com.example.dierenarts.service;

import com.example.dierenarts.dto.OwnerRequestDto;
import com.example.dierenarts.dto.PetRequestDto;
import com.example.dierenarts.exception.RecordNotFoundException;
import com.example.dierenarts.model.Owner;
import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;
    private PetService petService;

    public Iterable<Owner> getOwner(String name) {
        if (name.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findAllByNameContainingIgnoreCase(name);
        }
    }

    public Owner getOwner(Long id) {
        Optional<Owner> optionalOwner = repository.findById(id);

        if (optionalOwner.isPresent()) {
            return optionalOwner.get();
        } else {
            throw new RecordNotFoundException("There is no owner with this id.");
        }
    }

    public void deleteOwner(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("There is no owner with this id.");
        }
    }

    public Long addOwner(OwnerRequestDto ownerRequestDto) {
        Owner owner = new Owner();
        owner.setName(ownerRequestDto.getName());

        Owner newOwner = repository.save(owner);
        return newOwner.getId();
    }

    public void partialUpdatePet(Long id, Owner owner) {
        Optional<Owner> optionalOwner = repository.findById(id);

        if (optionalOwner.isPresent()) {
            Owner existingOwner = repository.findById(id).orElse(null);
            if (owner.getName() != null && !owner.getName().isEmpty()) {
                existingOwner.setName(owner.getName());
            }
            repository.save(existingOwner);
        } else {
            throw new RecordNotFoundException("There is no owner with this id");
        }
    }

}
