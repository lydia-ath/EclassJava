package com.example.app.dao;

import java.util.List;

import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;

public interface StudentClassDAO {

	void save(StudentClass sc);
	List<StudentClass> findAll();
	//StudentClass find(long Id);
	boolean findByClassName(String className);
	void save1(StudentClass studentClass);
}
