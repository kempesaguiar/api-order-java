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

import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.services.StockMovementService;
import com.kca.order.utils.BaseResponse;

@RestController
@RequestMapping("/stockmovements")
public class StockMovementController {
	
	private final StockMovementService service;

	@Autowired
	public StockMovementController(StockMovementService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<StockMovementShowDTO>>> findAllStockMovements() {
		BaseResponse<List<StockMovementShowDTO>> response = new BaseResponse<>();
		List<StockMovementShowDTO> usersList = this.service.listAllStockMovements();
		response.setResponse(usersList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<StockMovementShowDTO>> createUser(@RequestBody StockMovementCreateDTO dto) {
		BaseResponse<StockMovementShowDTO> response = new BaseResponse<>();
		StockMovementShowDTO savedStockMovement = this.service.createStockMovement(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStockMovement.getId()).toUri();
		response.setResponse(savedStockMovement, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}


}
