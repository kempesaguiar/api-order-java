package com.kca.order.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.kca.order.entities.dtos.itens.ItemCreateDTO;

@Entity
@Table(name = "tb_item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", unique = true, length = 20)
	private UUID id;
	
	@Column(name = "name", length = 300, nullable = false)
	private String name;
	
	public Item() {
		
	}
	
	public Item(ItemCreateDTO dto) {
		this.setName(dto.getName());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
