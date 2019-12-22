package com.subash.restservices.poc.restfulpoc;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;


@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentDAOService studentDAOService;
	
	@GetMapping("/allStudents")
	public List<Student> getAllStudents(){
		return studentDAOService.findAllStudents();
		
	}
	
	//spring 2.2.xxx version of hateoas implementation
	
//	@GetMapping("/student/{id}")
//	public EntityModel<Student> getStudentById(@PathVariable int id) {
//		
//		Student student = studentDAOService.findById(id);
//		if (student == null) {
//			throw new UserNotFoundException("User with id "+id + " "+ "not found" );
//		}
//		//Hateoas implementation  example
//		EntityModel<Student> model = new EntityModel<>(student);
//		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllStudents());
//		model.add(linkTo.withRel("all-users"));
//		return model;
//	}
	
	@GetMapping("/student/{id}")
	public Resource<Student> getStudentById(@PathVariable int id) {
		
		Student student = studentDAOService.findById(id);
		if (student == null) {
			throw new UserNotFoundException("User with id "+id + " "+ "not found" );
		}
		//Hateoas implementation  example
		Resource<Student> resource = new Resource<Student>(student);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllStudents());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
		Student savedStudent =studentDAOService.saveStudent(student);
		
		//this creates an uri location to the saved Student
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedStudent.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/student/{id}")
	public Student deleteStudentById(@PathVariable int id) {
		Student resp= studentDAOService.deleteById(id);
		if(resp == null) {
			throw new UserNotFoundException("User with id " +id+" cannot be found so hence cannot be deleted");
		}
		return resp;
	}

}
