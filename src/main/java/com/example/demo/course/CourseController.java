package com.example.demo.course;

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
@RequestMapping(value = "/api/v1/courses")
public class CourseController {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@GetMapping(value = "/{id}")
	public Course getStudentById(@PathVariable Long id) {
		Optional<Course> foundCourse =  courseRepository.findById(id);
		return foundCourse.get();
	}
	
	@GetMapping()
	public List<Course> getStudents() {
		List<Course> allCourses = courseRepository.findAll();
		return allCourses;
	}
	
	@PostMapping
	public Course saveStudent(@RequestBody Course course) {
		System.out.println("here");
		System.out.println("Course X: " + course);
		Course courseSaved = courseRepository.save(course);
		return courseSaved;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteStudentById(@PathVariable Long id) {
		courseRepository.deleteById(id);
		return "Course deleted successfully";
	}
	
	
}
