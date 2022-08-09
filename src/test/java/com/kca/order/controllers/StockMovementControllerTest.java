package com.kca.order.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kca.order.DataConfiguration;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.services.impl.StockMovementServiceImpl;
import com.kca.order.utils.StockMovementFactory;

@WebMvcTest(StockMovementController.class)
public class StockMovementControllerTest {
	
	@MockBean
    private StockMovementServiceImpl stockMovementService;

    @MockBean
    private DataSource datasource;
    
    @MockBean
    private DataConfiguration dataconfiguration;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        StockMovementShowDTO stockMovementShowDTO = StockMovementFactory.createStockMovementShowDTO();
        List<StockMovementShowDTO> stockMovementsList = new ArrayList<>();
        stockMovementsList.add(stockMovementShowDTO);

        when(this.stockMovementService.listAllStockMovements()).thenReturn(stockMovementsList);
        when(this.stockMovementService.createStockMovement(any())).thenReturn(stockMovementShowDTO);
    }

    @Test
    void findAll_ShouldReturnListOfStockMovementShowDTO_WhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/stockMovements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() throws Exception {
        when(this.stockMovementService.listAllStockMovements()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/stockMovements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void saveItem_ShouldReturnStockMovementShowDTO_WhenSuccessFul() throws Exception {
        StockMovementCreateDTO stockMovementDTO = StockMovementFactory.createStockMovementDTO();

        ResultActions result = this.mockMvc.perform(post("/stockMovements")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stockMovementDTO)));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.status", is(201)));
        result.andExpect(jsonPath("$.data.id", is("8e7552f2-fb99-41f2-894e-8c460c9c72a2")));
        result.andExpect(jsonPath("$.data.name", is("Test")));
    }
}



