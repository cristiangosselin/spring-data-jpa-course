package com.example.demo.teacher;

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
@RequestMapping(value = "/api/v1/teachers")
public class TeacherController {
	
	private TeacherRepository teacherRepository;
	
	@Autowired
	public TeacherController(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	@GetMapping(value = "/{id}")
	public Teacher getTeacherById(@PathVariable Long id) {
		Optional<Teacher> foundTeacher =  teacherRepository.findById(id);
//		System.out.println("CourseMaterial from Course X: " + foundCourse.get().getCourseMaterial());
		return foundTeacher.get();
	}
	
	@GetMapping()
	public List<Teacher> getTeachers() {
		List<Teacher> allTeachers = teacherRepository.findAll();
		return allTeachers;
	}
	
	@PostMapping
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		System.out.println("here");
		System.out.println("Teacher X: " + teacher);
		Teacher savedTeacher = teacherRepository.save(teacher);
		return savedTeacher;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteTeacherById(@PathVariable Long id) {
		teacherRepository.deleteById(id);
		return "Teacher deleted successfully";
	}

}
