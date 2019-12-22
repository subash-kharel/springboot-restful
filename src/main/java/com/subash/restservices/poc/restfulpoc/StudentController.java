package com.subash.restservices.poc.restfulpoc;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentDAOService studentDAOService;
	
	@GetMapping("/allStudents")
	public List<Student> getAllStudents(){
		return studentDAOService.findAllStudents();
		
	}
	
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		return studentDAOService.findById(id);
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
