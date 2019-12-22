package com.subash.restservices.poc.restfulpoc;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private Integer Id;
	@Size(min =2,message ="Should be at least of size 2")
	private String name;
	@Past
	private Date dob;

}
