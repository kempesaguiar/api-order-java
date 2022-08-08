package com.kca.order.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kca.order.DataConfiguration;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;
import com.kca.order.services.impl.UserServiceImpl;
import com.kca.order.utils.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
//@ActiveProfiles("test")
class UserControllerTest {
	
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private DataSource datasource;
    
    @MockBean
    private DataConfiguration dataconfiguration;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        UserShowDTO userShowDTO = UserFactory.createUserShowDTO();
        List<UserShowDTO> usersList = new ArrayList<>();
        usersList.add(userShowDTO);

        when(this.userService.listAllUsers()).thenReturn(usersList);
        when(this.userService.createUser(any())).thenReturn(userShowDTO);
    }

    @Test
    void findAll_ShouldReturnListOfUserShowDTO_WhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() throws Exception {
        when(this.userService.listAllUsers()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void saveUser_ShouldReturnUserShowDTO_WhenSuccessFul() throws Exception {
        UserCreateDTO userDTO = UserFactory.createUserDTO();

        ResultActions result = this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.status", is(201)));
        result.andExpect(jsonPath("$.data.id", is("8e7552f2-fb99-41f2-894e-8c460c9c72a2")));
        result.andExpect(jsonPath("$.data.name", is("Test")));
    }
}

