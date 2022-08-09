package com.kca.order.utils;

import java.sql.Date;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.Order;
import com.kca.order.entities.User;
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;

public class OrderFactory {
	
	public static Order createOrderToBeSave() {
        Order order = new Order();
        //Date hoje = new Date();
        //order.setCreationDate(new Date(Date.valueOf(s)));
        order.setItem(new Item());
        order.setQuantity(1);
        order.setUser(new User());
        return order;
    }

    public static OrderCreateDTO createOrderDTO() {
    	OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
    	//orderCreateDTO.setCreationDate(Date.valueOf(String.valueOf("10/08/2022")));
    	orderCreateDTO.setItem(new Item());
    	orderCreateDTO.setQuantity(1);
    	orderCreateDTO.setUser(new User());
        return orderCreateDTO;
    }

    public static OrderShowDTO createOrderShowDTO() {
    	OrderShowDTO dto = new OrderShowDTO();
        dto.setId(UUID.fromString("8e7552f2-fb99-41f2-894e-8c460c9c72a2"));
        //dto.setCreationDate(Date.valueOf("05/08/2022"));
        dto.setItem(new Item());
        dto.setQuantity(1);
        dto.getUser();
        return dto;
    }

}
