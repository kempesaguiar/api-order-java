package com.kca.order.services;

import java.util.List;
import java.util.UUID;

import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;

public interface OrderService {
	
	OrderShowDTO createOrder(OrderCreateDTO order);
	
	List<OrderShowDTO> listAllOrders();
	
	OrderShowDTO updateOrder(UUID id, OrderCreateDTO requestorder);
	
	void deleteOrder(UUID id);

}
