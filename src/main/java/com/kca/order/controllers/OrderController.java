package com.kca.order.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.services.OrderService;
import com.kca.order.services.StockMovementService;
import com.kca.order.utils.BaseResponse;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService service;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<OrderShowDTO>>> findAllOrders() {
		BaseResponse<List<OrderShowDTO>> response = new BaseResponse<>();
		List<OrderShowDTO> ordersList = this.service.listAllOrders();
		response.setResponse(ordersList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<OrderShowDTO>> createUser(@RequestBody OrderCreateDTO dto) {
		BaseResponse<OrderShowDTO> response = new BaseResponse<>();
		OrderShowDTO savedOrder = this.service.createOrder(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedOrder.getId()).toUri();
		response.setResponse(savedOrder, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}



}
