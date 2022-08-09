package com.kca.order.services;

import java.util.List;
import java.util.UUID;

import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;

public interface UserService {
	
	UserShowDTO createUser(UserCreateDTO user);

	List<UserShowDTO> listAllUsers();
	
	UserShowDTO updateUser(UUID id, UserCreateDTO user);
	
	void deleteUser(UUID id);

}
