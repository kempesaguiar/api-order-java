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

import com.kca.order.entities.StockMovement;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.StockMovementRepository;
import com.kca.order.utils.StockMovementFactory;

@ExtendWith(SpringExtension.class)
public class StockMovementServiceImplTest {
	
	@InjectMocks
    private StockMovementServiceImpl stockMovementService;

    @Mock
    private MapStructUserMapper mapper;

    @Mock
    private StockMovementRepository stockMovementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        StockMovement stockMovementToBeSave = StockMovementFactory.createStockMovementToBeSave();
        StockMovementShowDTO stockMovementShowDTO = StockMovementFactory.createStockMovementShowDTO();
        List<StockMovement> stockMovementsList = new ArrayList<>();
        stockMovementsList.add(stockMovementToBeSave);

        when(this.stockMovementRepository.findAll()).thenReturn(stockMovementsList);
        when(this.stockMovementRepository.saveAndFlush(any())).thenReturn(stockMovementToBeSave);
        when(this.mapper.stockMovementCreateDTOToStockMovement(any())).thenReturn(stockMovementToBeSave);
        when(this.mapper.stockMovementToStockMovementShowDTO(any())).thenReturn(stockMovementShowDTO);
    }

    @Test
    void findAll_ShouldReturnAListOfStockMovementShowDTO_WhenSuccessful() {
        List<StockMovementShowDTO> stockMovementsReponse = this.stockMovementService.listAllStockMovements();

        assertNotNull(stockMovementsReponse);
        assertFalse(stockMovementsReponse.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAnEmptyList_WhenSuccessful() {
        when(this.stockMovementRepository.findAll()).thenReturn(new ArrayList<>());

        List<StockMovementShowDTO> stockMovementsReponse = this.stockMovementService.listAllStockMovements();

        assertTrue(stockMovementsReponse.isEmpty());
    }

    @Test
    void saveStockMovement_ShouldSaveAndFlush_whenSuccessful() {
        StockMovementCreateDTO stockMovementDTO = StockMovementFactory.createStockMovementDTO();

        StockMovementShowDTO savedStockMovement = this.stockMovementService.createStockMovement(stockMovementDTO);

        assertNotNull(savedStockMovement);
    }
}
