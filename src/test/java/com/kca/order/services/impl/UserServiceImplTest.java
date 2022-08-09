package com.kca.order.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kca.order.entities.User;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.UserRepository;
import com.kca.order.utils.UserFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private MapStructUserMapper mapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User userToBeSave = UserFactory.createUserToBeSave();
        UserShowDTO userShowDTO = UserFactory.createUserShowDTO();
        List<User> usersList = new ArrayList<>();
        usersList.add(userToBeSave);

        when(this.userRepository.findAll()).thenReturn(usersList);
        when(this.userRepository.saveAndFlush(any())).thenReturn(userToBeSave);
        when(this.mapper.userCreateDTOToUser(any())).thenReturn(userToBeSave);
        when(this.mapper.userToUserShowDTO(any())).thenReturn(userShowDTO);
    }

    @Test
    void findAll_ShouldReturnAListOfUserShowDTO_WhenSuccessful() {
        List<UserShowDTO> usersReponse = this.userService.listAllUsers();

        assertNotNull(usersReponse);
        assertFalse(usersReponse.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserShowDTO> usersReponse = this.userService.listAllUsers();

        assertTrue(usersReponse.isEmpty());
    }

    @Test
    void saveUser_ShouldSaveAndFlush_whenSuccessful() {
        UserCreateDTO userDTO = UserFactory.createUserDTO();

        UserShowDTO savedUser = this.userService.createUser(userDTO);

        assertNotNull(savedUser);
    }
}
