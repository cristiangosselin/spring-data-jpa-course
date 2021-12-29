package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {
	
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@GetMapping(value = "/{id}")
	public Student getStudentById(@PathVariable Long id) {
		Optional<Student> foundStudent =  studentRepository.findById(id);
		return foundStudent.get();
	}
	
	@GetMapping(value = "/email/{email}")
	public Student getStudentByEmail(@PathVariable String email) {
		Student foundStudents =  studentRepository.findByEmail(email);
		return foundStudents;
	}
	
	@GetMapping(value = "/firstName/{firstName}")
	public List<Student> getStudentByFirstName(@PathVariable String firstName) {
		List<Student> foundStudents =  studentRepository.findByFirstName(firstName);
		return foundStudents;
	}
	
	@GetMapping()
	public List<Student> getStudents() {
		List<Student> allStudents = studentRepository.findAll();
		return allStudents;
	}
	
	@PostMapping
	public Student saveStudent(@RequestBody Student student) {
		System.out.println("here");
		System.out.println("Student X: " + student);
		Student studentSaved = studentRepository.save(student);
		return studentSaved;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteStudentById(@PathVariable Long id) {
		studentRepository.deleteById(id);
		return "Student deleted successfully";
	}



}
