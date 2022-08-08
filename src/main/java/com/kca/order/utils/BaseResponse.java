package com.kca.order.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class BaseResponse<T> {

	private T data;
	private Integer status;
	private List<T> errors;
	
	public BaseResponse() {
	}
	
	public T getData() {
		return data;
	}

	public Integer getStatus() {
		return status;
	}

	public List<T> getErrors() {
		return errors;
	}

	public void setResponse(T obj, HttpStatus status) {
		this.data = obj;
		this.status = status.value();
	}
}