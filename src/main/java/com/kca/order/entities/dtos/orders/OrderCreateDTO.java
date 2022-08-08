package com.kca.order.entities.dtos.orders;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import com.kca.order.entities.Item;
import com.kca.order.entities.User;

public class OrderCreateDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	private Item item;
	private int quantity;
	private User user;
	
	public OrderCreateDTO() {
		
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
        OrderCreateDTO entity = (OrderCreateDTO) o;
        return Objects.equals(this.creationDate, entity.creationDate) &&
                Objects.equals(this.item, entity.item) &&
                Objects.equals(this.quantity, entity.quantity) &&
                Objects.equals(this.user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, item, quantity, user);
    }
	
	

}
