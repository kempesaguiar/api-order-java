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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.services.StockMovementService;
import com.kca.order.utils.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/stockmovements")
public class StockMovementController {
	
	private static final Logger logger = LogManager.getLogger(StockMovementController.class);
	
	private final StockMovementService service;

	@Autowired
	public StockMovementController(StockMovementService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get All StockMovements", description = "By default returns all saved stockMovements.")
	public ResponseEntity<BaseResponse<List<StockMovementShowDTO>>> findAllStockMovements() {
		logger.info("Método GET da API de StockMovement foi chamado");
		BaseResponse<List<StockMovementShowDTO>> response = new BaseResponse<>();
		List<StockMovementShowDTO> usersList = this.service.listAllStockMovements();
		response.setResponse(usersList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<StockMovementShowDTO>> createStockMovement(@RequestBody StockMovementCreateDTO dto) {
		logger.info("Método POST da API de StockMovement foi chamado");
		BaseResponse<StockMovementShowDTO> response = new BaseResponse<>();
		StockMovementShowDTO savedStockMovement = this.service.createStockMovement(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStockMovement.getId()).toUri();
		response.setResponse(savedStockMovement, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse<StockMovementShowDTO>> updateStockMovement(@PathVariable(name = "id") UUID id, @RequestBody StockMovementCreateDTO dto) {
		logger.info("Método PUT da API de StockMovement foi chamado");
		BaseResponse<StockMovementShowDTO> response = new BaseResponse<>();
		StockMovementShowDTO savedStockMovement = this.service.createStockMovement(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStockMovement.getId()).toUri();
		response.setResponse(savedStockMovement, HttpStatus.OK);
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStockMovement(@PathVariable(name = "id") UUID id) {
		logger.info("Método DELETE da API de StockMovement foi chamado");
		try {
			this.service.deleteStockMovement(id);
		} catch(Exception e) {
			System.out.println("Error delete by User " + id);
			logger.error("Error delete StockMovement");
		}
	}


}
