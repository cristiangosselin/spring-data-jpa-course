package com.example.demo.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.courseMaterial.CourseMaterial;
import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "course")
@Table(name = "course")
public class Course {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	@Column(name = "title", 
			nullable = false)
    private String title;
    private Integer credit;

    @JsonIgnoreProperties(value = {"course"}, allowSetters = true)
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;
    
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"courses"})
    private Teacher teacherId;
    
    @JsonIgnoreProperties(value = {"courses"})
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "studen_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id"
            )
    )
    private List<Student> students = new ArrayList<>();;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public CourseMaterial getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(CourseMaterial courseMaterial) {
		courseMaterial.setCourse(this);
		this.courseMaterial = courseMaterial;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (!(obj instanceof Course )) return false;
        return id != null && id.equals(((Course) obj).getId());
	}
}
