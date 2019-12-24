package com.subash.restservices.poc.restfulpoc;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class blog {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	private String title;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private StudentJpa studentJpa;

}
