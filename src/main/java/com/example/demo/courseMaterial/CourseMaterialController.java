package com.example.demo.courseMaterial;

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

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;

@RestController
@RequestMapping(value = "/api/v1/coursesMaterial")
public class CourseMaterialController {
	
	private CourseMaterialRepository courseMaterialRepository;
	
	@Autowired
	public CourseMaterialController(CourseMaterialRepository courseMaterialRepository) {
		this.courseMaterialRepository = courseMaterialRepository;
	}
	
	@GetMapping(value = "/{id}")
	public CourseMaterial getCourseMaterialsById(@PathVariable Long id) {
		Optional<CourseMaterial> foundCourseMaterial =  courseMaterialRepository.findById(id);
		return foundCourseMaterial.get();
	}
	
	@GetMapping()
	public List<CourseMaterial> getCourseMaterials() {
		List<CourseMaterial> allCoursesMaterial = courseMaterialRepository.findAll();
		return allCoursesMaterial;
	}
	
	@PostMapping
	public CourseMaterial saveCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
		System.out.println("here");
		System.out.println("CourseMaterial X: " + courseMaterial);
		CourseMaterial courseMaterialSaved = courseMaterialRepository.save(courseMaterial);
		return courseMaterialSaved;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deletecourseMaterialById(@PathVariable Long id) {
		courseMaterialRepository.deleteById(id);
		return "courseMaterial deleted successfully";
	}
	
}
