package com.kca.order.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.services.OrderService;
import com.kca.order.utils.BaseResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	private final OrderService service;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<OrderShowDTO>>> findAllOrders() {
		logger.info("Método GET da API de Order foi chamado");
		BaseResponse<List<OrderShowDTO>> response = new BaseResponse<>();
		List<OrderShowDTO> ordersList = this.service.listAllOrders();
		response.setResponse(ordersList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<OrderShowDTO>> createUser(@RequestBody OrderCreateDTO dto) {
		logger.info("Método POST da API de Order foi chamado");
		BaseResponse<OrderShowDTO> response = new BaseResponse<>();
		OrderShowDTO savedOrder = this.service.createOrder(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedOrder.getId()).toUri();
		response.setResponse(savedOrder, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse<OrderShowDTO>> updateUser(UUID id, @RequestBody OrderCreateDTO dto) {
		logger.info("Método PUT da API de Order foi chamado");
		BaseResponse<OrderShowDTO> response = new BaseResponse<>();
		OrderShowDTO savedOrder = this.service.updateOrder(id, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedOrder.getId()).toUri();
		response.setResponse(savedOrder, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping
	public void deleteOrder(UUID id) {
		logger.info("Método DELETE da API de Order foi chamado");
		try {
			this.service.deleteOrder(id);
		} catch(Exception e) {
			System.out.println("Error delete by User " + id);
			logger.error("Error delete Order");
		}
	}



}
