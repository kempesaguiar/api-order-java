package com.kca.order.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kca.order.entities.Item;
import com.kca.order.entities.dtos.itens.ItemCreateDTO;
import com.kca.order.entities.dtos.itens.ItemShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.ItemRepository;
import com.kca.order.utils.ItemFactory;

@ExtendWith(SpringExtension.class)
public class ItemServiceImplTest {
	
	@InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private MapStructUserMapper mapper;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Item itemToBeSave = ItemFactory.createItemToBeSave();
        ItemShowDTO itemShowDTO = ItemFactory.createItemShowDTO();
        List<Item> itensList = new ArrayList<>();
        itensList.add(itemToBeSave);

        when(this.itemRepository.findAll()).thenReturn(itensList);
        when(this.itemRepository.saveAndFlush(any())).thenReturn(itemToBeSave);
        when(this.mapper.itemCreateDTOToItem(any())).thenReturn(itemToBeSave);
        when(this.mapper.itemToItemShowDTO(any())).thenReturn(itemShowDTO);
    }

    @Test
    void findAll_ShouldReturnAListOfUserShowDTO_WhenSuccessful() {
        List<ItemShowDTO> itensReponse = this.itemService.listAllItens();

        assertNotNull(itensReponse);
        assertFalse(itensReponse.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() {
        when(this.itemRepository.findAll()).thenReturn(new ArrayList<>());

        List<ItemShowDTO> itensReponse = this.itemService.listAllItens();

        assertTrue(itensReponse.isEmpty());
    }

    @Test
    void saveItem_ShouldSaveAndFlush_whenSuccessful() {
        ItemCreateDTO itemDTO = ItemFactory.createItemDTO();

        ItemShowDTO savedItem = this.itemService.createItem(itemDTO);

        assertNotNull(savedItem);
    }
}
