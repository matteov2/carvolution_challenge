package com.example.carvolution.service;

import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.repository.AdressRepository;
import com.example.carvolution.repository.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserAdressServiceTest {

    @InjectMocks
    private UserAdressService userAdressService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdressRepository adressRepository; // TODO: unused

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        assertEquals(userAdressService.getAllUsers().size(), 2);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getUser() { // TODO: missing exception test, unneccesarry to test stuff from the mock
        Adress adress = new Adress(1, "Musterstrasse 29", "8000", "Bern");

        when(userRepository.findById(1)).thenReturn(Optional.of(new User(1, "firstname1", "lastname1", adress)));

        assertEquals(1, userAdressService.getUser(1).getId()); // TODO: expecte/actual wrong order
        assertEquals(userAdressService.getUser(1).getAdress(), adress);
        assertEquals(userAdressService.getUser(1).getFirstname(), "firstname1");
        assertEquals(userAdressService.getUser(1).getLastname(), "lastname1");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void saveUser() {
        Adress adress = new Adress(1, "Musterstrasse 29", "8000", "Bern");
        User userToSave = new User(1, "firstname1", "lastname1", adress);

        final var actual = userAdressService.saveUser(userToSave);

        assertThat(actual).usingRecursiveComparison().isEqualTo(userToSave);
        verify(userRepository, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void updateAdress() {
        Adress adressToUpdate = new Adress(1, "Musterstrasse 29", "8000", "Bern");
        Adress updatedAdress = new Adress(5, "Teststrasse 29", "3000", "Bern");
        User user = new User(1, "firstname1", "lastname1", adressToUpdate);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertEquals(userAdressService.updateAdress(updatedAdress, user.getId()), user.getAdress());
        verify(userRepository, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }
}