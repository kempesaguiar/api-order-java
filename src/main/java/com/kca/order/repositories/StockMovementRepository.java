package com.kca.order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kca.order.entities.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

}
