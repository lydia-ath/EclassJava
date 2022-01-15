package com.example.app.daoImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentDAO;
import com.example.app.domain.Student;

public class StudentDaoImplTest {
	
	private StudentDAO studentdao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		studentdao = new StudentDAOImpl();
		
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<Student> students = studentdao.findAll();
		assertEquals(8, students.size());
		
	}
	
	@Test void findByFirstName() {
		  
		  boolean result = studentdao.findByStudentFirstName("JOHN");
		  assertFalse(result); 

	  }
	  
	  
	  @Test void findByFirstName1() {
	  
		  boolean result = studentdao.findByStudentLastName("Athanasiou");
		  assertTrue(result); 
	  
	  }

}
