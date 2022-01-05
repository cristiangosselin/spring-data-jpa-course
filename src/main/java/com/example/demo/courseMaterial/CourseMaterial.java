package com.example.demo.courseMaterial;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//    @JsonManagedReference
    @JsonIgnoreProperties({"courseMaterial"})
    @OneToOne(
    		fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = true
    )
//    @MapsId
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

	@Override
	public int hashCode() {
		return Objects.hash(course, id, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseMaterial other = (CourseMaterial) obj;
		return Objects.equals(course, other.course) && Objects.equals(id, other.id) && Objects.equals(url, other.url);
	}
	
	
	
	
    
    
}
