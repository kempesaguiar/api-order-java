package com.kca.order.services;

import java.util.List;

import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;

public interface OrderService {
	
	OrderShowDTO createOrder(OrderCreateDTO order);
	
	List<OrderShowDTO> listAllOrders();

}
