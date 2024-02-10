package com.example.demo.services.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
public class GenricResponse {
	
	
	public int code;
	public String message;
	public Object object;

	
	public GenricResponse(int code, String message, Object object) {
		this.code = code;
		this.message = message;
		this.object = object;
	}
}
