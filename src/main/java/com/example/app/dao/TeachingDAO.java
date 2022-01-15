package com.example.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.app.domain.Course;
import com.example.app.domain.Professor;
import com.example.app.domain.Teaching;

public interface TeachingDAO {
	
	void save(Teaching teaching);
	void delete(Teaching teaching);
	List<Teaching> findAll();
	Professor getProfessor(Teaching teaching);
	Course getCourse(Teaching teaching);

}
