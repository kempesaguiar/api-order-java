package com.kca.order.services;

import java.util.List;

import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;

public interface ItemService {
	
	ItemShowDTO createItem(ItemCreateDTO item);
	
	List<ItemShowDTO> listAllItens();

}
