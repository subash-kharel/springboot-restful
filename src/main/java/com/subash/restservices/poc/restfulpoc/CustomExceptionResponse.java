package com.subash.restservices.poc.restfulpoc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String details;

}
