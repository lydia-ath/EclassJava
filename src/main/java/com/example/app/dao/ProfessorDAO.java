package com.example.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.app.domain.Course;
import com.example.app.domain.Professor;
import com.example.app.domain.Teaching;

public interface ProfessorDAO {

	void save(Professor professor);
	void delete(Professor professor);
	void delete1(Professor professor);
	List<Professor> findAll();
	boolean findByProfessorName(String name);
	List<Professor> findByProfessorLastName(String name);
	void save1(Professor professor);
	
	/** check correlation professor-teachings*/
	List<Teaching> findProfessorTeachings(String name);
	boolean deleteProfessors(long professorId);
}
