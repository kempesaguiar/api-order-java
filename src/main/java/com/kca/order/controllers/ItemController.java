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

import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.services.ItemService;
import com.kca.order.utils.BaseResponse;

@RestController
@RequestMapping("/itens")
public class ItemController {
	
	private final ItemService service;

	@Autowired
	public ItemController(ItemService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<ItemShowDTO>>> findAllItens() {
		BaseResponse<List<ItemShowDTO>> response = new BaseResponse<>();
		List<ItemShowDTO> itensList = this.service.listAllItens();
		response.setResponse(itensList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<ItemShowDTO>> createUser(@RequestBody ItemCreateDTO dto) {
		BaseResponse<ItemShowDTO> response = new BaseResponse<>();
		ItemShowDTO savedItem = this.service.createItem(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedItem.getId()).toUri();
		response.setResponse(savedItem, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}


}
