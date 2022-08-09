package com.kca.order.services;

import java.util.List;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;

public interface ItemService {
	
	ItemShowDTO createItem(ItemCreateDTO item);
	
	List<ItemShowDTO> listAllItens();
	
	ItemShowDTO updateItem(UUID id, ItemCreateDTO item) throws Exception;
	
	void deleteItem(UUID id);
	
	Item listItem(UUID id) throws Exception;

}
