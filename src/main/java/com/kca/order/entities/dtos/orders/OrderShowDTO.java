package com.kca.order.entities.dtos.orders;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.Order;
import com.kca.order.entities.User;

public class OrderShowDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private Date creationDate;
	private Item item;
	private int quantity;
	private User user;
	
	public OrderShowDTO() {
		
	}
	
	public OrderShowDTO(Order order) {
        this.id = order.getId();
        this.creationDate = order.getCreationDate();
        this.item = order.getItem();
        this.quantity = order.getQuantity();
        this.user = order.getUser();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderShowDTO entity = (OrderShowDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.creationDate, entity.creationDate) &&
                Objects.equals(this.item, entity.item) &&
                Objects.equals(this.quantity, entity.quantity) &&
                Objects.equals(this.user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, item, quantity, user);
    }
	
	

}
