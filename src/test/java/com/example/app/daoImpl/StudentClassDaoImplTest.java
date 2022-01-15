package com.example.app.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentClassDAO;
import com.example.app.domain.StudentClass;

public class StudentClassDaoImplTest {
	
	private StudentClassDAO studentclassdao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		studentclassdao = new StudentClassDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	/*@Test
	void findAll() {
		
		List<StudentClass> studentClasses = studentclassdao.findAll();
		assertEquals(5, studentClasses.size());
		
	}*/
	
	@Test
	void findByClassName() {
		boolean result = studentclassdao.findByClassName("A1");
		assertTrue(result); 
	}
	

}

