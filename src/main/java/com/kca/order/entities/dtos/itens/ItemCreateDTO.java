package com.kca.order.entities.dtos.itens;

import java.io.Serializable;
import java.util.Objects;

public class ItemCreateDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	public ItemCreateDTO() {
		
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
        ItemCreateDTO entity = (ItemCreateDTO) o;
        return Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
