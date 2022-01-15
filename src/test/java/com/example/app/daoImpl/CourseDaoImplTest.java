package com.example.app.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.domain.Course;

public class CourseDaoImplTest {
	
private CourseDAO coursedao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
		
		coursedao = new CourseDaoImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<Course> courses = coursedao.findAll();
		assertEquals(3, courses.size());
		
	}
		
	@Test void findByCourseName() {
		 
		 List<Course> courses=coursedao.findCourseName("PHYSICS"); 
		 assertEquals(1,courses.size()); 
		 
	 }
	 
	/*test domain logic --> return all teachings for course PHYSICS*/
	/**SUCCESS*/
	@Test
	  void findCourseTeachings() {
		  assertEquals(3, coursedao.findCourseTeachings("PHYSICS").size());
	}

}
