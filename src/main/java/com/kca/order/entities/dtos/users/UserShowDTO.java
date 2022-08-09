package com.kca.order.entities.dtos.users;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.kca.order.entities.User;

public class UserShowDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private UUID id;
    private String name;
    private String email;

    public UserShowDTO() {}
    
    public UserShowDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
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
        UserShowDTO entity = (UserShowDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
