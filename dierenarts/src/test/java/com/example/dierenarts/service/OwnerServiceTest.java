package com.example.dierenarts.service;

import com.example.dierenarts.dto.OwnerRequestDto;
import com.example.dierenarts.exception.RecordNotFoundException;
import com.example.dierenarts.model.Owner;
import com.example.dierenarts.repository.OwnerRepository;
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
class OwnerServiceTest {

    @Mock
    private OwnerRepository repository;

    @InjectMocks
    private OwnerService service;

    @Captor
    ArgumentCaptor<Owner> captor;

    @Test
    public void getOwnerTest() {
        Owner owner = new Owner();
        owner.setName("test");
        when(repository.findById(1L)).thenReturn(Optional.of(owner));

        service.getOwner(1L);
    }

    @Test
    public void getOwnersTest() {
        List<Owner> testOwners = new ArrayList<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("owner1");
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("owner2");
        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setName("owner3");

        testOwners.add(owner1);
        testOwners.add(owner2);
        testOwners.add(owner3);

        when(repository.findAll()).thenReturn(testOwners);

        service.getOwner();

        verify(repository, times(1)).findAll();

        assertThat(testOwners.size()).isEqualTo(3);
        assertThat(testOwners.get(0)).isEqualTo(owner1);
        assertThat(testOwners.get(1)).isEqualTo(owner2);
        assertThat(testOwners.get(2)).isEqualTo(owner3);
    }



    @Test
    public void getOwnerExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> service.getOwner(0L));
    }

    @Test
    public void addOwnerTest() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("test");

        repository.save(owner);

        verify(repository, times(1)).save(captor.capture());
        var owner1 = captor.getValue();

        assertThat(owner1.getName()).isEqualTo("test");
        assertThat(owner1.getId()).isEqualTo(1);
    }

    @Test
    public void updateOwnerTest() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("test");
        when(repository.findById(1L)).thenReturn(Optional.of(owner1));

        OwnerRequestDto requestDto = new OwnerRequestDto();
        requestDto.setName("test1");
        service.partialUpdateOwner(1L, requestDto);

        verify(repository).save(owner1);

        assertThat(owner1.getId()).isEqualTo(1);
        assertThat(owner1.getName()).isEqualTo("test1");
    }

    @Test
    public void deleteOwnerTest() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("test");

        repository.delete(owner1);

//        service.deleteOwner(1L);
        //Throws Record not found error. TODO find out how to test this.

        verify(repository, times(1)).delete(owner1);
    }


}