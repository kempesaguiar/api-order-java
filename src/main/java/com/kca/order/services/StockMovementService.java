package com.kca.order.services;

import java.util.List;

import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;

public interface StockMovementService {
	
	StockMovementShowDTO createStockMovement(StockMovementCreateDTO stockMovement);
	
	List<StockMovementShowDTO> listAllStockMovements();

}
