package com.example.demo.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "teacher")
@Table(name = "teacher")
public class Teacher {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "first_name")
    private String firstName;
	
    private String lastName;

    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"teacherId"})
    private List<Course> courses = new ArrayList<>(); 
    
    
	public Teacher() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		courses.forEach(item -> item.setTeacherId(this));
		this.courses = courses;
	}
	
    public void addComment(Course course) {
        courses.add(course);
        course.setTeacherId(this);
    }
 
    public void removeComment(Course course) {
    	courses.remove(course);
    	course.setTeacherId(null);
    }

    
}
