package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByEmail(String email);
	
	List<Student> findByFirstName(String firstName);
	
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
//    Optional<Student> findStudentByEmail(String email);
//
//    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
//    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
//            String firstName, Integer age);
//
//    @Query(
//            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
//            nativeQuery = true)
//    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
//            @Param("firstName") String firstName,
//            @Param("age") Integer age);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Student u WHERE u.id = ?1")
//    int deleteStudentById(Long id);
}
