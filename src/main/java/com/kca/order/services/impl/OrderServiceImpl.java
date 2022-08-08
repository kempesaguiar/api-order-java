package com.kca.order.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kca.order.entities.Order;
import com.kca.order.entities.StockMovement;
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.OrderRepository;
import com.kca.order.repositories.StockMovementRepository;
import com.kca.order.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository repository;
	private MapStructUserMapper userMapper;
	
	@Autowired
	public OrderServiceImpl(OrderRepository repository, MapStructUserMapper userMapper) {
		super();
		this.repository = repository;
		this.userMapper = userMapper;
	}
	
	@Transactional
	@Override
	public OrderShowDTO createOrder(OrderCreateDTO orderRequestDTO) {
		Order orderToBeSaved = this.userMapper.orderCreateDTOToOrder(orderRequestDTO);
		orderToBeSaved = this.repository.saveAndFlush(orderToBeSaved);
		return this.userMapper.orderToOrderShowDTO(orderToBeSaved);
	}
	
	@Override
	public List<OrderShowDTO> listAllOrders() {
		return this.repository.findAll()
				.stream().map(order -> this.userMapper.orderToOrderShowDTO(order))
				.collect(Collectors.toList());
	}


}
