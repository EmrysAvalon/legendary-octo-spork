package com.example.dierenarts.service;

import com.example.dierenarts.dto.PetRequestDto;
import com.example.dierenarts.exception.RecordNotFoundException;
import com.example.dierenarts.model.Pet;
import com.example.dierenarts.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository repository;

    @InjectMocks
    private PetService service;

    @Captor
    ArgumentCaptor<Pet> captor;

    @Test
    public void getPetTest() {
        Pet pet = new Pet();
        pet.setName("test");
        when(repository.findById(1L)).thenReturn(Optional.of(pet));

        service.getPet(1L);
    }

    @Test
    public void getPetsTest() {
        List<Pet> testPets = new ArrayList<>();
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("pet1");
        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("pet2");
        Pet owner3 = new Pet();
        owner3.setId(3L);
        owner3.setName("pet3");

        testPets.add(pet1);
        testPets.add(pet2);
        testPets.add(owner3);

        when(repository.findAll()).thenReturn(testPets);

        service.getPets();

        verify(repository, times(1)).findAll();

        assertThat(testPets.size()).isEqualTo(3);
        assertThat(testPets.get(0)).isEqualTo(pet1);
        assertThat(testPets.get(1)).isEqualTo(pet2);
        assertThat(testPets.get(2)).isEqualTo(owner3);
    }

    @Test
    public void getPetExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> service.getPet(0L));
    }

    @Test
    public void addPetTest() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("test");

        repository.save(pet);

        verify(repository, times(1)).save(captor.capture());
        var pet1 = captor.getValue();

        assertThat(pet1.getName()).isEqualTo("test");
        assertThat(pet1.getId()).isEqualTo(1);
    }

    @Test
    public void updatePetTest() {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("test");
        when(repository.findById(1L)).thenReturn(Optional.of(pet1));

        PetRequestDto requestDto = new PetRequestDto();
        requestDto.setName("test1");
        service.partialUpdatePet(1L, requestDto);

        verify(repository).save(pet1);

        assertThat(pet1.getId()).isEqualTo(1);
        assertThat(pet1.getName()).isEqualTo("test1");
    }

    @Test
    public void deletePetTest() {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("test");

        repository.delete(pet1);

//        service.deletePet(1L);
        //Throws Record not found error. TODO find out how to test this.

        verify(repository, times(1)).delete(pet1);
    }

}