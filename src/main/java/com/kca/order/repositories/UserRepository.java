package com.kca.order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kca.order.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	

}
