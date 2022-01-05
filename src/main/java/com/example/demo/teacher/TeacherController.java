package com.example.demo.teacher;

import java.util.ArrayList;
import java.util.Iterator;
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
@RequestMapping(value = "/api/v1/teachers")
public class TeacherController {
	
	private TeacherRepository teacherRepository;
	private CourseRepository courseRepository;
	
	@Autowired
	public TeacherController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
		this.teacherRepository = teacherRepository;
		this.courseRepository = courseRepository;
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
		List<Course> listCourses = new ArrayList<>();
		if (teacher.getCourses().size() != 0) {
			for (Course c : teacher.getCourses()) {
				System.out.println("here");
				if (c.getId() != null) {
					Optional<Course> auxCourse = courseRepository.findById(c.getId());
					listCourses.add(auxCourse.get());
				} else {
					listCourses.add(c);
				}
			}
		}
		teacher.setCourses(listCourses);
		Teacher savedTeacher = teacherRepository.save(teacher);
		return savedTeacher;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteTeacherById(@PathVariable Long id) {
		teacherRepository.deleteById(id);
		return "Teacher deleted successfully";
	}

}
