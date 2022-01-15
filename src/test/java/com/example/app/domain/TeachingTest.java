package com.example.app.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.TeachingDAO;
import com.example.app.daoImpl.TeachingDAOImpl;

public class TeachingTest {
		
	Teaching teaching;
	Professor professor;
	Course course;
	ScheduleSlot shceduleslot;
	
	@BeforeEach
	public void setUp() {
		teaching  = new Teaching();
		professor = new Professor("YANNIS", "SPATHAKIS","jhn", "1184", 18);
		course = new Course("MATHEMATICS", "6");
		shceduleslot = new ScheduleSlot(Days.Monday, Hours.first_Hour, teaching);
		teaching.setCourse(course);
	}
	
	@Test
	void addTeaching() {
 
		assertNotEquals(null, teaching);
	}
	
	@Test
	void setProfessor() {

		teaching.setProfessor(professor);
		assertTrue(teaching.getProfessor()!=null);
	}
	
	@Test
	void successfuleRemovalOfProfessor() {

		teaching.deleteProfessor();
		assertTrue(teaching.getProfessor()== null);
	}
	
	@Test
	void setTeachingHours() {
		
		teaching.setTeachingHours(2);
		assertTrue(2==teaching.getTeachingHours());
	}
	
	@Test
	void setCourse() {
	
		teaching.setCourse(course);
		assertTrue(teaching.getCourse()!=null);
		
	}
	
	@Test
	void getCourse() {

		teaching.setCourse(course);
		assertTrue("MATHEMATICS".equals(teaching.getCourse().getCourseName()));
		
	}
	
	@Test
	void adddTeahing() {
		
		professor.addTeaching(teaching);
		assertEquals(1, professor.getTeachings().size());
	}
	
	@Test
	void gerProfessor() {
		teaching.setProfessor(professor);
		assertEquals("SPATHAKIS", teaching.getProfessor().getLastname());
	}
	
	@Test
	void addScheduleSlot() {
		
		teaching.addScheduleSlot(shceduleslot);
		assertTrue(teaching.getScheduleSlots().size()!=0);		
		
	}
	
	//@SuppressWarnings("unused")
	@Test
	private void successfullAdditionofScheduleSlot() {
		assertTrue(teaching.getScheduleSlots().contains(shceduleslot));
    	Assertions.assertSame(shceduleslot, teaching.getScheduleSlots().get(0));
			
	}
	
	@Test
	void successfuleRemovalOfCourse() {
		
		teaching.deleteCourse();
		assertTrue(teaching.getCourse()==null);		
		
	}
	
	@Test
	void successfuleRemovalOfScheduleSlot() {
		
		teaching.deleteScheduleSlot(shceduleslot);
		assertTrue(teaching.getScheduleSlots().size()==0);		
		
	}
	
	@Test
	void checkNumberOfScheduleSlots() {
		
		assertEquals(0, teaching.getScheduleSlots().size());	
		
	}
	
	@AfterEach
	public void cleanUpEach(){
		professor.removeTeaching(teaching);
		System.out.println("Data base will be intialized..");
		
	}

}
