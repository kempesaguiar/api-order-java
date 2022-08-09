package com.kca.order.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kca.order.entities.Item;
import com.kca.order.entities.Order;
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.OrderRepository;
import com.kca.order.repositories.StockMovementRepository;
import com.kca.order.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository repository;
	private StockMovementRepository stockMovementrepository;
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
		
		// Verifica se tem o item no estoque
		Item item = orderRequestDTO.getItem();
		// Query Repository
		int qtd = stockMovementrepository.qtdStockItem(item.getId());
		Order orderToBeSaved = this.userMapper.orderCreateDTOToOrder(orderRequestDTO);
		
		if (qtd > 0) {
			orderToBeSaved = this.repository.saveAndFlush(orderToBeSaved);
			return this.userMapper.orderToOrderShowDTO(orderToBeSaved);
		} else {
			new Exception("Not Qtd Stock");
			return this.userMapper.orderToOrderShowDTO(orderToBeSaved);
		}
	}
	
	@Override
	public List<OrderShowDTO> listAllOrders() {
		return this.repository.findAll()
				.stream().map(order -> this.userMapper.orderToOrderShowDTO(order))
				.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public OrderShowDTO updateOrder(UUID id, OrderCreateDTO orderRequestDTO) {
		Order orderToBeSaved = this.userMapper.orderCreateDTOToOrder(orderRequestDTO);
		orderToBeSaved = this.repository.saveAndFlush(orderToBeSaved);
		return this.userMapper.orderToOrderShowDTO(orderToBeSaved);
	}
	
	@Transactional
	@Override
	public void deleteOrder(UUID id) {
		repository.deleteById(id);
	}


}
