package org.jsp.springBootUserFoodOrder.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private T data;
	private String message;
	private int statusCode;

}
