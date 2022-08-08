package com.kca.order.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kca.order.entities.StockMovement;
import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;
import com.kca.order.entities.dtos.stockmovements.StockMovementShowDTO;
import com.kca.order.mappers.MapStructUserMapper;
import com.kca.order.repositories.StockMovementRepository;
import com.kca.order.services.StockMovementService;

@Service
public class StockMovementServiceImpl implements StockMovementService {
	
	private StockMovementRepository repository;
	private MapStructUserMapper userMapper;
	
	@Autowired
	public StockMovementServiceImpl(StockMovementRepository repository, MapStructUserMapper userMapper) {
		super();
		this.repository = repository;
		this.userMapper = userMapper;
	}
	
	@Transactional
	@Override
	public StockMovementShowDTO createStockMovement(StockMovementCreateDTO stockMovementRequestDTO) {
		StockMovement stockMovementToBeSaved = this.userMapper.stockMovementCreateDTOToStockMovement(stockMovementRequestDTO);
		stockMovementToBeSaved = this.repository.saveAndFlush(stockMovementToBeSaved);
		return this.userMapper.stockMovementToStockMovementShowDTO(stockMovementToBeSaved);
	}
	
	@Override
	public List<StockMovementShowDTO> listAllStockMovements() {
		return this.repository.findAll()
				.stream().map(stockMovement -> this.userMapper.stockMovementToStockMovementShowDTO(stockMovement))
				.collect(Collectors.toList());
	}

}
