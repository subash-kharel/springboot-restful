package com.subash.restservices.poc.restfulpoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentDAOService {

	public static List<Student> students = new ArrayList<Student>();
	
	private static int id =2;
	
	static {
		students.add(new Student(1,"Subash",new Date()));
		students.add(new Student(2,"Aurora", new Date()));
	}
	
	public List<Student> findAllStudents() {
		
		return students;
	}
	
	public Student findById(int id) {
		for(Student student:students) {
			if(student.getId() == id) {
				return student;
			}
		}
		return null;
	}
	
	public Student saveStudent(Student student) {
		if(student.getId()==null) {
			id++;
			student.setId(id);
		}
		
		students.add(student);
		return student;
	}
}
