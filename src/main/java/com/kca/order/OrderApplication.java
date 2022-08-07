package com.kca.order;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderApplication {
	
	@Autowired
    private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
