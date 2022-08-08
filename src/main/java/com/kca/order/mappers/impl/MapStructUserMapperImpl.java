package com.kca.order.mappers.impl;

import org.springframework.stereotype.Component;

import com.kca.order.entities.Item;
import com.kca.order.entities.Order;
import com.kca.order.entities.StockMovement;
import com.kca.order.entities.User;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.entities.dtos.orders.OrderCreateDTO;
import com.kca.order.entities.dtos.orders.OrderShowDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.entities.dtos.users.UserCreateDTO;
import com.kca.order.entities.dtos.users.UserShowDTO;
import com.kca.order.mappers.MapStructUserMapper;

@Component
public class MapStructUserMapperImpl implements MapStructUserMapper {
	
	@Override
	public User userCreateDTOToUser(UserCreateDTO userCreateDTO) {
		return new User(userCreateDTO);
	}

	@Override
	public UserShowDTO userToUserShowDTO(User user) {
		return new UserShowDTO(user);
	}
	
	@Override
	public Item itemCreateDTOToItem(ItemCreateDTO itemCreateDTO) {
		return new Item(itemCreateDTO);
	}

	@Override
	public ItemShowDTO itemToItemShowDTO(Item item) {
		return new ItemShowDTO(item);
	}
	
	@Override
	public StockMovement stockMovementCreateDTOToStockMovement(StockMovementCreateDTO smCreateDTO) {
		return new StockMovement(smCreateDTO);
	}

	@Override
	public StockMovementShowDTO stockMovementToStockMovementShowDTO(StockMovement stockMovement) {
		return new StockMovementShowDTO(stockMovement);
	}
	
	@Override
	public Order orderCreateDTOToOrder(OrderCreateDTO orderCreateDTO) {
		return new Order(orderCreateDTO);
	}

	@Override
	public OrderShowDTO orderToOrderShowDTO(Order order) {
		return new OrderShowDTO(order);
	}
	
	

}
