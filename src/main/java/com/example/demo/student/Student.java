package com.example.demo.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name = "students")
@Table(
		name = "students",
		uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email") //If there are more than one pass an array {})
        }
			)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "first_name"
    )
    private String firstName;

    @Column(
            name = "last_name"
    )
    private String lastName;

    @Column(
    		unique = true,
            name = "email"
    )
    private String email;

    @Column(
            name = "age"
    )
    private Integer age;
    
    @JsonIgnoreProperties(value = {"students"})
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName,
                   String lastName,
                   String email,
                   Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		courses.forEach(item -> item.addStudents(this));
		this.courses = courses;
	}

	@Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(age, email, firstName, id, lastName, courses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return Objects.equals(age, other.age) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(courses, other.courses);
	}
    
    
}
