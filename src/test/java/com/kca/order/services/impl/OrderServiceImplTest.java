package com.kca.order.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kca.order.entities.Order;
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.OrderRepository;
import com.kca.order.utils.OrderFactory;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {
	
	@InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private MapStructUserMapper mapper;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Order orderToBeSave = OrderFactory.createOrderToBeSave();
        OrderShowDTO orderShowDTO = OrderFactory.createOrderShowDTO();
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(orderToBeSave);

        when(this.orderRepository.findAll()).thenReturn(ordersList);
        when(this.orderRepository.saveAndFlush(any())).thenReturn(orderToBeSave);
        when(this.mapper.orderCreateDTOToOrder(any())).thenReturn(orderToBeSave);
        when(this.mapper.orderToOrderShowDTO(any())).thenReturn(orderShowDTO);
    }

    @Test
    void findAll_ShouldReturnAListOfOrderShowDTO_WhenSuccessful() {
        List<OrderShowDTO> ordersReponse = this.orderService.listAllOrders();

        assertNotNull(ordersReponse);
        assertFalse(ordersReponse.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() {
        when(this.orderRepository.findAll()).thenReturn(new ArrayList<>());

        List<OrderShowDTO> ordersReponse = this.orderService.listAllOrders();

        assertTrue(ordersReponse.isEmpty());
    }

    @Test
    void saveStockMovement_ShouldSaveAndFlush_whenSuccessful() {
        OrderCreateDTO orderDTO = OrderFactory.createOrderDTO();

        OrderShowDTO savedOrder = this.orderService.createOrder(orderDTO);

        assertNotNull(savedOrder);
    }
}

