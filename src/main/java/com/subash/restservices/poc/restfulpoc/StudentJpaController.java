package com.subash.restservices.poc.restfulpoc;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
@RequestMapping("jpa/students")
public class StudentJpaController {
	
	@Autowired
	private StudentJpaService studentJpaService;
	
	@GetMapping("/allStudents")
	public List<StudentJpa> getAllStudents(){
		return studentJpaService.findAllStudents();
		
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
	public Resource<StudentJpa> getStudentById(@PathVariable int id) {
		
		Optional<StudentJpa> student = studentJpaService.findById(id);
		if (!student.isPresent()) {
			throw new UserNotFoundException("User with id "+id + " "+ "not found" );
		}
		//Hateoas implementation  example
		Resource<StudentJpa> resource = new Resource<StudentJpa>(student.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllStudents());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<StudentJpa> saveStudent(@Valid @RequestBody StudentJpa student) {
		StudentJpa savedStudent =studentJpaService.saveStudent(student);
		
		//this creates an uri location to the saved Student
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedStudent.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/student/{id}")
	public void deleteStudentById(@PathVariable int id) {
	   studentJpaService.deleteById(id);

	}
	
	@GetMapping("/student/{id}/blog")
	public List<blog> getPostforAStudent(@PathVariable int id) {
		List<blog> studentsBlog =studentJpaService.getBlogForAStudent(id);
		if(studentsBlog ==null) {
			throw new UserNotFoundException("User with id "+id + " "+ "not found" );
		}
		return studentsBlog;
	}
	
	@PostMapping("/saveBlog/{userId}/blog")
	public ResponseEntity<StudentJpa> saveStudent(@Valid @PathVariable int userId, @RequestBody blog studentblog) {
		blog studentsBlog=studentJpaService.saveblogForAStudent(userId, studentblog);
		
		//this creates an uri location to the saved Student
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(studentsBlog.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}