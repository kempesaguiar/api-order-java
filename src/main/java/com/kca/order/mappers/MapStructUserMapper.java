package com.kca.order.mappers;

import org.mapstruct.Mapper;

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

@Mapper(componentModel = "spring")
public interface MapStructUserMapper {
	
	User userCreateDTOToUser(UserCreateDTO userCreateDTO);

    UserShowDTO userToUserShowDTO(User user);
    
    Item itemCreateDTOToItem(ItemCreateDTO itemCreateDTO);
    
    ItemShowDTO itemToItemShowDTO(Item item);
    
    StockMovement stockMovementCreateDTOToStockMovement(StockMovementCreateDTO smCreateDTO);
    
    StockMovementShowDTO stockMovementToStockMovementShowDTO(StockMovement stockMovement);
    
    Order orderCreateDTOToOrder(OrderCreateDTO orderCreateDTO);
    
    OrderShowDTO orderToOrderShowDTO(Order order);
    

}
