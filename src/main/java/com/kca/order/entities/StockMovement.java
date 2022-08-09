package com.kca.order.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.kca.order.entities.dtos.stockmovements.StockMovementCreateDTO;

@Entity
@Table(name = "tb_stock_movement")
public class StockMovement implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", unique = true, length = 20)
	private UUID id;
	
	@Column(name = "creationDate", nullable = false)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date creationDate;
	
	// Item Join
	@OneToOne(targetEntity = Item.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Item item;
	
	//@Column(name = "item", length = 60, nullable = false)
	//private String item;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	public StockMovement() {
		
	}
	
	public StockMovement(StockMovementCreateDTO dto) {
		this.setCreationDate(dto.getCreationDate());
		this.setItem(dto.getItem());
		this.setQuantity(dto.getQuantity());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	

}
