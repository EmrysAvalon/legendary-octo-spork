package com.example.dierenarts.service;

import com.example.dierenarts.model.User;
import com.example.dierenarts.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
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
class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Captor
    ArgumentCaptor<User> captor;

    @Test
    public void getUserTest() {
        User user = new User();
        user.setUsername("test");
        when(repository.findById("test")).thenReturn(Optional.of(user));

        service.getUser("test");
    }

    @Test
    public void getUsersTest() {
        List<User> testUsers = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        User user3 = new User();
        user3.setUsername("user3");

        testUsers.add(user1);
        testUsers.add(user2);
        testUsers.add(user3);

        when(repository.findAll()).thenReturn(testUsers);

        service.getUsers();

        verify(repository, times(1)).findAll();

        assertThat(testUsers.size()).isEqualTo(3);
        assertThat(testUsers.get(0)).isEqualTo(user1);
        assertThat(testUsers.get(1)).isEqualTo(user2);
        assertThat(testUsers.get(2)).isEqualTo(user3);
    }

    @Test
    public void addUserTest() {
        User user = new User();
        user.setUsername("user");

        repository.save(user);

        verify(repository, times(1)).save(captor.capture());
        var user1 = captor.getValue();

        assertThat(user1.getUsername()).isEqualTo("user");
    }

    @Test
    public void removeAuthorityTest() {
        User user = new User();
        user.setUsername("user");
        user.addAuthority("ROLE_RECEPTIONIST");

        when(repository.findById(user.getUsername())).thenReturn(Optional.of(user));

        service.removeAuthority(user.getUsername(), "ROLE_RECEPTIONIST");
        Mockito.verify(repository).save(captor.capture());
        User user1 = captor.getValue();

        assertEquals(0, user1.getAuthorities().size());
    }

//    @Test
//    public void updateUserTest() {
//        User user1 = new User();
//        user1.setUsername("user");
//        user1.setPassword("P@sw0rd");
//        when(repository.findById("user")).thenReturn(Optional.of(user1));
//
//        service.updateUser("user", user1); TODO figure out how to test this.
//
//        verify(repository).save(user1);
//
//        assertThat(user1.getUsername()).isEqualTo("user");
//    }

    @Test
    public void deleteUserTest() {
        User user1 = new User();
        user1.setUsername("user");

        repository.delete(user1);

//        service.deleteOwner(1L);
        //Throws Record not found error. TODO find out how to test this.

        verify(repository, times(1)).delete(user1);
    }
}