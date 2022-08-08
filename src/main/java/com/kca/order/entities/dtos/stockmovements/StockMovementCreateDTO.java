package com.kca.order.entities.dtos.stockmovements;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import com.kca.order.entities.Item;

public class StockMovementCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	private Item item;
	private int quantity;
	
	public StockMovementCreateDTO() {
		
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
        StockMovementCreateDTO entity = (StockMovementCreateDTO) o;
        return Objects.equals(this.creationDate, entity.creationDate) &&
                Objects.equals(this.item, entity.item) &&
                Objects.equals(this.quantity, entity.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, item, quantity);
    }
	
	

}
