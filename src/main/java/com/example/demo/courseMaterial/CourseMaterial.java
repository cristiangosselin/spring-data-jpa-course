package com.example.demo.courseMaterial;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "course_material")
@Table(name = "course_material")
public class CourseMaterial {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	@Column(name = "id")
    private Long id;
    private String url;

//    @JsonIgnore
//    @JsonBackReference
    @JsonManagedReference
    @OneToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;

	public Long getCourseMaterialId() {
		return id;
	}

	public void setCourseMaterialId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "CourseMaterial [courseMaterialId=" + id + ", url=" + url + ", course=" + course + "]";
	}
	
	
    
    
}
