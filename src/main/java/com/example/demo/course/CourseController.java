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

import com.example.demo.courseMaterial.CourseMaterial;
import com.example.demo.courseMaterial.CourseMaterialRepository;

@RestController
@RequestMapping(value = "/api/v1/courses")
public class CourseController {
	
	private CourseRepository courseRepository;
	private CourseMaterialRepository courseMaterialRepository;

	
	@Autowired
	public CourseController(CourseRepository courseRepository, CourseMaterialRepository courseMaterialRepository) {
		this.courseRepository = courseRepository;
		this.courseMaterialRepository = courseMaterialRepository;
	}
	
	@GetMapping(value = "/{id}")
	public Course getStudentById(@PathVariable Long id) {
		Optional<Course> foundCourse =  courseRepository.findById(id);
//		System.out.println("CourseMaterial from Course X: " + foundCourse.get().getCourseMaterial());
		return foundCourse.get();
	}
	
	@GetMapping()
	public List<Course> getStudents() {
		List<Course> allCourses = courseRepository.findAll();
		return allCourses;
	}
	
	@PostMapping
	public CourseMaterial saveStudent(@RequestBody Course course) {
		System.out.println("here");
		System.out.println("Course X: " + course);
		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setUrl(course.getCourseMaterial().getUrl());
		Course newCourse = new Course();
		newCourse.setTitle(course.getTitle());
		newCourse.setCredit(course.getCredit());
//		newCourse.setTeacherId(course.getTeacherId());
		courseMaterial.setCourse(newCourse);
		System.out.println("CourseMaterial X: " + courseMaterial);
		CourseMaterial courseSaved = courseMaterialRepository.save(courseMaterial);
		return courseSaved;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteStudentById(@PathVariable Long id) {
		courseRepository.deleteById(id);
		return "Course deleted successfully";
	}
	
	
}
