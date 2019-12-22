package com.subash.restservices.poc.restfulpoc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private Integer Id;
	private String name;
	private Date dob;

}
