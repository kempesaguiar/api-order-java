package com.kca.order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kca.order.entities.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {
	
	@Query("Select Sum(quantity) from tb_stock_movement where item = :id")
	public int qtdStockItem(@Param("id") UUID id);

}
