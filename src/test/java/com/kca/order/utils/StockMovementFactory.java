package com.kca.order.utils;

import java.sql.Date;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.StockMovement;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;

public class StockMovementFactory {
	
	public static StockMovement createStockMovementToBeSave() {
        StockMovement stockMovement = new StockMovement();
        //stockMovement.setCreationDate(Date.valueOf("05/08/2022"));
        stockMovement.setItem(new Item());
        stockMovement.setQuantity(1);
        return stockMovement;
    }

    public static StockMovementCreateDTO createStockMovementDTO() {
    	StockMovementCreateDTO stockMovementCreateDTO = new StockMovementCreateDTO();
    	//stockMovementCreateDTO.setCreationDate(Date.valueOf("05/08/2022"));
    	stockMovementCreateDTO.setItem(new Item());
    	stockMovementCreateDTO.setQuantity(1);
        return stockMovementCreateDTO;
    }

    public static StockMovementShowDTO createStockMovementShowDTO() {
    	StockMovementShowDTO dto = new StockMovementShowDTO();
        dto.setId(UUID.fromString("8e7552f2-fb99-41f2-894e-8c460c9c72a2"));
        //dto.setCreationDate(Date.valueOf("05/08/2022"));
        dto.setItem(new Item());
        dto.setQuantity(1);
        return dto;
    }

}
