package com.kca.order.utils;

import java.util.UUID;

import com.kca.order.entities.User;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;

public class UserFactory {
	
	public static User createUserToBeSave() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@test.com");
        return user;
    }

    public static UserCreateDTO createUserDTO() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName("Test");
        userCreateDTO.setEmail("test@test.com");
        return userCreateDTO;
    }

    public static UserShowDTO createUserShowDTO() {
        UserShowDTO dto = new UserShowDTO();
        dto.setId(UUID.fromString("8e7552f2-fb99-41f2-894e-8c460c9c72a2"));
        dto.setName("Test");
        return dto;
    }

}
