package com.kca.order.entities.dtos.itens;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.kca.order.entities.Item;
import com.kca.order.entities.dtos.users.UserShowDTO;

public class ItemShowDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String name;
	
	public ItemShowDTO(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemShowDTO entity = (ItemShowDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
	
	
	
	

}
