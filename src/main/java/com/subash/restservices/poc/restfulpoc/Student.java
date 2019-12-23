package com.subash.restservices.poc.restfulpoc;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "All info about the student")
@Data
@AllArgsConstructor
@NoArgsConstructor
//another way to ignore private and sensitive fields as the part of response
//@JsonIgnoreProperties(value = {"dob"})
public class Student {
	
	private Integer Id;
	@Size(min =2,message ="Should be at least of size 2")
	@ApiModelProperty(notes =" should have atleast two letters")
	private String name;
	@Past
	@ApiModelProperty(notes =" birthdate should be before todays date")
	//add json ignore if you dont want this as the part of the response
	@JsonIgnore
	private Date dob;

}
