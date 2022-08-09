package com.kca.order.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kca.order.DataConfiguration;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.services.impl.ItemServiceImpl;
import com.kca.order.utils.ItemFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@WebMvcTest(ItemController.class)
//@ActiveProfiles("test")
class ItemControllerTest {
	
    @MockBean
    private ItemServiceImpl itemService;

    @MockBean
    private DataSource datasource;
    
    @MockBean
    private DataConfiguration dataconfiguration;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        ItemShowDTO itemShowDTO = ItemFactory.createItemShowDTO();
        List<ItemShowDTO> itensList = new ArrayList<>();
        itensList.add(itemShowDTO);

        when(this.itemService.listAllItens()).thenReturn(itensList);
        when(this.itemService.createItem(any())).thenReturn(itemShowDTO);
    }

    @Test
    void findAll_ShouldReturnListOfItemShowDTO_WhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/itens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() throws Exception {
        when(this.itemService.listAllItens()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/itens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void saveItem_ShouldReturnItemShowDTO_WhenSuccessFul() throws Exception {
        ItemCreateDTO itemDTO = ItemFactory.createItemDTO();

        ResultActions result = this.mockMvc.perform(post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemDTO)));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.status", is(201)));
        result.andExpect(jsonPath("$.data.id", is("8e7552f2-fb99-41f2-894e-8c460c9c72a2")));
        result.andExpect(jsonPath("$.data.name", is("Test")));
    }
}


