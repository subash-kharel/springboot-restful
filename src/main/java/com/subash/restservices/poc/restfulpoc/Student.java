package com.subash.restservices.poc.restfulpoc;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "All info about the student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private Integer Id;
	@Size(min =2,message ="Should be at least of size 2")
	@ApiModelProperty(notes =" should have atleast two letters")
	private String name;
	@Past
	@ApiModelProperty(notes =" birthdate should be before todays date")
	private Date dob;

}
