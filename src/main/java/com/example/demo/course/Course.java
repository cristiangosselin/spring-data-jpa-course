package com.example.demo.course;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.courseMaterial.CourseMaterial;
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
	@Column(name = "id")
    private Long id;
    private String title;
    private Integer credit;

//    @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "course"
//            orphanRemoval = true
//            optional = false

    )
    private CourseMaterial courseMaterial;
    
    
//    @ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
//	@JsonIgnoreProperties("courses")
//    private Teacher teacherId;

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
		this.courseMaterial = courseMaterial;
	}

//	public Teacher getTeacherId() {
//		return teacherId;
//	}
//
//	public void setTeacherId(Teacher teacherId) {
//		this.teacherId = teacherId;
//	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", credit=" + credit + ", courseMaterial=" + courseMaterial +"]";
	}

	
    
	
    
}
