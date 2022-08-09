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
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.services.impl.OrderServiceImpl;
import com.kca.order.utils.OrderFactory;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private DataSource datasource;
    
    @MockBean
    private DataConfiguration dataconfiguration;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        OrderShowDTO orderShowDTO = OrderFactory.createOrderShowDTO();
        List<OrderShowDTO> ordersList = new ArrayList<>();
        ordersList.add(orderShowDTO);

        when(this.orderService.listAllOrders()).thenReturn(ordersList);
        when(this.orderService.createOrder(any())).thenReturn(orderShowDTO);
    }

    @Test
    void findAll_ShouldReturnListOfOrderShowDTO_WhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() throws Exception {
        when(this.orderService.listAllOrders()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    void saveItem_ShouldReturnOrderShowDTO_WhenSuccessFul() throws Exception {
        OrderCreateDTO orderDTO = OrderFactory.createOrderDTO();

        ResultActions result = this.mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDTO)));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.status", is(201)));
        result.andExpect(jsonPath("$.data.id", is("8e7552f2-fb99-41f2-894e-8c460c9c72a2")));
        result.andExpect(jsonPath("$.data.name", is("Test")));
    }
}
