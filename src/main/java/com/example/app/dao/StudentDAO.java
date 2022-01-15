package com.example.app.dao;

import java.util.List;
import com.example.app.domain.Student;

public interface StudentDAO {
	
	void save(Student student);
	void delete(Student student);
	List<Student> findAll();
	//Student find2(long studentId);
	Student find(long studentId);
	void save1(Student student);
	boolean findByStudentFirstName(String firstName);
	boolean findByStudentLastName(String lastName);
	
}

