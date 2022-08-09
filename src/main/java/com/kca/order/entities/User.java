package com.kca.order.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.kca.order.entities.dtos.users.UserCreateDTO;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", unique = true, length = 20)
	private UUID id;
	
	@Column(name = "name", length = 300, nullable = false)
	private String name;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	
	public User() {
		
	}
	
	public User(UserCreateDTO dto) {
		this.setName(dto.getName());
		this.setEmail(dto.getEmail());
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
	

}
