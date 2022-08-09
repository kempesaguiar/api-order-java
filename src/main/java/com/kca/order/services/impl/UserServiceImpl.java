package com.kca.order.services.impl;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kca.order.entities.User;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.UserRepository;
import com.kca.order.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	private MapStructUserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository repository, MapStructUserMapper userMapper) {
		super();
		this.repository = repository;
		this.userMapper = userMapper;
	}
	
	@Transactional
	@Override
	public UserShowDTO createUser(UserCreateDTO userRequestDTO) {
		User userToBeSaved = this.userMapper.userCreateDTOToUser(userRequestDTO);
		userToBeSaved = this.repository.saveAndFlush(userToBeSaved);
		return this.userMapper.userToUserShowDTO(userToBeSaved);
	}
	
	@Override
	public List<UserShowDTO> listAllUsers() {
		return this.repository.findAll()
				.stream().map(user -> this.userMapper.userToUserShowDTO(user))
				.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public UserShowDTO updateUser(UUID id, UserCreateDTO userRequestDTO) {
		User userToBeSaved = this.userMapper.userCreateDTOToUser(userRequestDTO);
		userToBeSaved = this.repository.saveAndFlush(userToBeSaved);
		return this.userMapper.userToUserShowDTO(userToBeSaved);
	}
	
	@Transactional
	@Override
	public void deleteUser(UUID id) {
		repository.deleteById(id);
	}

}
