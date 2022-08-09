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

import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.services.ItemService;
import com.kca.order.utils.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/itens")
public class ItemController {
	
	private static final Logger logger = LogManager.getLogger(ItemController.class);
	
	private final ItemService service;

	@Autowired
	public ItemController(ItemService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get All Itens", description = "By default returns all saved itens.")
	public ResponseEntity<BaseResponse<List<ItemShowDTO>>> findAllItens() {
		logger.info("Método GET da API de Itens foi chamado");
		BaseResponse<List<ItemShowDTO>> response = new BaseResponse<>();
		logger.warn("Método GET da API de Itens chamou a service");
		List<ItemShowDTO> itensList = this.service.listAllItens();
		response.setResponse(itensList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	@Operation(summary = "Create Itens", description = "saved itens.")
	public ResponseEntity<BaseResponse<ItemShowDTO>> createItem(@RequestBody ItemCreateDTO dto) {
		logger.info("Método POST da API de Itens foi chamado");
		BaseResponse<ItemShowDTO> response = new BaseResponse<>();
		ItemShowDTO savedItem = this.service.createItem(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedItem.getId()).toUri();
		response.setResponse(savedItem, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update Itens", description = "saved itens.")
	public ResponseEntity<BaseResponse<ItemShowDTO>> updateItem(@PathVariable(name = "id") UUID id, @RequestBody ItemCreateDTO dto) throws Exception {
		logger.info("Método PUT da API de Itens foi chamado");
		BaseResponse<ItemShowDTO> response = new BaseResponse<>();
		ItemShowDTO savedItem = this.service.updateItem(id, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedItem.getId()).toUri();
		response.setResponse(savedItem, HttpStatus.OK);
		return ResponseEntity.created(uri).body(response);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a item", description = "Deletes a item if the id exists.")
	public void deleteItem(@PathVariable(name = "id") UUID id) {
		logger.info("Método DELETE da API de Itens foi chamado");
		try {
			this.service.deleteItem(id);
		} catch(Exception e) {
			System.out.println("Error in delete by User " + id);
			logger.error("Error delete Item");
		}
	}

}
