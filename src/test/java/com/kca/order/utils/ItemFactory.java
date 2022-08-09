package com.kca.order.utils;

import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;

public class ItemFactory {
	
	public static Item createItemToBeSave() {
        Item item = new Item();
        item.setName("Test");
        return item;
    }

    public static ItemCreateDTO createItemDTO() {
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
        itemCreateDTO.setName("Test");
        return itemCreateDTO;
    }

    public static ItemShowDTO createItemShowDTO() {
        ItemShowDTO dto = new ItemShowDTO();
        dto.setId(UUID.fromString("8e7552f2-fb99-41f2-894e-8c460c9c72a2"));
        dto.setName("Test");
        return dto;
    }

}
