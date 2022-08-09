package com.kca.order.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

import com.kca.order.DataConfiguration;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;
import com.kca.order.services.UserService;
import com.kca.order.utils.BaseResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<UserShowDTO>>> findAllUsers() {
		BaseResponse<List<UserShowDTO>> response = new BaseResponse<>();
		List<UserShowDTO> usersList = this.service.listAllUsers();
		response.setResponse(usersList, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<UserShowDTO>> createUser(@RequestBody UserCreateDTO dto) {
		BaseResponse<UserShowDTO> response = new BaseResponse<>();
		UserShowDTO savedUser = this.service.createUser(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		response.setResponse(savedUser, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse<UserShowDTO>> updateUser(@PathVariable UUID id, @RequestBody UserCreateDTO dto) {
		BaseResponse<UserShowDTO> response = new BaseResponse<>();
		UserShowDTO savedUser = this.service.updateUser(id, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		response.setResponse(savedUser, HttpStatus.CREATED);
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(UUID id) {
		try {
			this.service.deleteUser(id);
		} catch (Exception e) {
			System.out.println("Error delete by User " + id);
		}
	}

}
