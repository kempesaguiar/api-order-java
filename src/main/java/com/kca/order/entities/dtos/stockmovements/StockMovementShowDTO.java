package com.kca.order.entities.dtos.stockmovements;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.StockMovement;
import com.kca.order.entities.User;
import com.kca.order.entities.dtos.users.UserShowDTO;

public class StockMovementShowDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private Date creationDate;
	private Item item;
	private int quantity;
	
	public StockMovementShowDTO() {
		
	}
	
	public StockMovementShowDTO(StockMovement stockmovement) {
        this.id = stockmovement.getId();
        this.creationDate = stockmovement.getCreationDate();
        this.item = stockmovement.getItem();
        this.quantity = stockmovement.getQuantity();
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockMovementShowDTO entity = (StockMovementShowDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.creationDate, entity.creationDate) &&
                Objects.equals(this.item, entity.item) &&
                Objects.equals(this.quantity, entity.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, item, quantity);
    }
	
	

}
