package com.subash.restservices.poc.restfulpoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentJpaService {

@Autowired
private StudentRepository studentRepository;

@Autowired
private BlogJpaRepository blogJpaRepository;
	
	public List<StudentJpa> findAllStudents() {
		
		return studentRepository.findAll();
	}
	
	public Optional<StudentJpa> findById(int id) {
		Optional<StudentJpa> studentJpa= studentRepository.findById(id);
		if(studentJpa.isPresent()) {
			return studentJpa;
		}
		return null;
	}
	
	public StudentJpa saveStudent(StudentJpa studentJpa) {

		return studentRepository.save(studentJpa);
	}
	
	public void deleteById(int id) {
		
		studentRepository.deleteById(id);
		
		
	}
	
	public List<blog> getBlogForAStudent(int id) {
		Optional<StudentJpa> student =studentRepository.findById(id);
		if(!student.isPresent()) {
			return null;
		}
		return student.get().getBlog();
		
	}
	
	public blog saveblogForAStudent(int id,blog studentBlog) {
		Optional<StudentJpa> student =studentRepository.findById(id);
		if(!student.isPresent()) {
			return null;
		}
	     studentBlog.setStudentJpa(student.get());
	    return  blogJpaRepository.save(studentBlog);
	}

}
