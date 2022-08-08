package com.kca.order.services;

import java.util.List;

import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;

public interface UserService {
	
	UserShowDTO createUser(UserCreateDTO user);

	List<UserShowDTO> listAllUsers();

}
