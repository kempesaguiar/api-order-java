package com.kca.order.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kca.order.entities.Item;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.ItemRepository;
import com.kca.order.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemrepository;
	private MapStructUserMapper userMapper;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemrepository, MapStructUserMapper userMapper) {
		super();
		this.itemrepository = itemrepository;
		this.userMapper = userMapper;
	}
	
	@Transactional
	@Override
	public ItemShowDTO createItem(ItemCreateDTO itemRequestDTO) {
		Item itemToBeSaved = this.userMapper.itemCreateDTOToItem(itemRequestDTO);
		itemToBeSaved = this.itemrepository.saveAndFlush(itemToBeSaved);
		return this.userMapper.itemToItemShowDTO(itemToBeSaved);
	}
	
	@Override
	public List<ItemShowDTO> listAllItens() {
		return this.itemrepository.findAll()
				.stream().map(item -> this.userMapper.itemToItemShowDTO(item))
				.collect(Collectors.toList());
	}
	
	@Override
	public Item listItem(UUID id) throws Exception {
		return itemrepository.findById(id)
				.orElseThrow(() -> new Exception("ID not found"));
	}
	
	@Transactional
	@Override
	public ItemShowDTO updateItem(UUID id, ItemCreateDTO itemRequestDTO) throws Exception {
		
		Item searchItem = listItem(id);
		Item itemToBeSaved = this.userMapper.itemCreateDTOToItem(itemRequestDTO);
		itemToBeSaved = this.itemrepository.saveAndFlush(itemToBeSaved);
		return this.userMapper.itemToItemShowDTO(itemToBeSaved);
	}
	
	@Transactional
	@Override
	public void deleteItem(UUID id) {
		this.itemrepository.deleteById(id);
	}

}
