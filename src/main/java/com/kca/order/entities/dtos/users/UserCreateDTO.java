package com.kca.order.entities.dtos.users;

import java.io.Serializable;
import java.util.Objects;

public class UserCreateDTO implements Serializable {
	
	private String name;
	private String email;
	
	public UserCreateDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDTO entity = (UserCreateDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

}
