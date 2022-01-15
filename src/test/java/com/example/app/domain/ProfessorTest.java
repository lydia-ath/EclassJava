package com.example.app.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.ProfessorDAO;
import com.example.app.daoImpl.ProfessorDAOImpl;

public class ProfessorTest {
	
	Professor professor;
	Teaching teaching;
	Teaching teaching1;
	Evaluation evaluation;
	
	@BeforeEach
	public void setUp() {
		//Initializer initializer = new Initializer(); 
		//initializer.prepareData();
		professor = new Professor("SOFIA", "DROUGKA","sofi", "1234", 25);
		teaching = new Teaching();
		teaching1 = new Teaching();
		evaluation = new Evaluation();
	}
	
	 @Test
	 void addNewProfessor() {
		assertNotEquals(null, professor);
	 }
	
	
	 @Test 
	  void verifyUserName() { 
		  String username=professor.getUsername();
	      assertTrue("sofi".equals(username)); 
		 }
	  
	  @Test 
	  void addTeaching() { 
		  teaching.setTeachingHours(2);
		  teaching1.setTeachingHours(2);
		  professor.addTeaching(teaching);	
		  professor.addTeaching(teaching1);	
		  Course course = new Course();
	      course.addTeaching(teaching);
	      successfullAdditionofTeaching(course,teaching1);
		 
	      }
	
	  @Test
	  void removeTeaching() { 
		  teaching.setTeachingHours(2);
		  professor.removeTeaching(teaching);	
		  assertTrue(professor.getTeachings().isEmpty()); 
	      }
	  
	  @Test
	  void addEvaluation() {
		  
		  evaluation.setGrade(17);
		  evaluation.setComments(Comments.Good);
		  professor.completesEvaluation(evaluation);
		  professor.completesEvaluation(evaluation);
		  assertTrue(!professor.getEvaluations().isEmpty()); 
	  }
	  
	  @Test
	  void removeEvaluation() {
		  professor.removeEvaluation(evaluation);
		  expectedEvaluationsSize(professor,0);		  
	  }
	 
	  @Test
	  void setSpeciality() {
		  professor.setSpecialty("Informatics");
		  assertEquals("Informatics",professor.getSpecialty());
	  }
	  
	  @Test
	  void setHoursOfEmployment() {
		  professor.setHoursOfEmployement(15);
		  assertEquals(15,professor.getHoursOfEmployement());
	  }
	  
	private void successfullAdditionofTeaching(Course course, Teaching teaching){
	    Assertions.assertFalse(course.getTeachings().contains(teaching));
	}
	  
    @SuppressWarnings("unused")
	private void expectedTeachingsSize(Professor professore, int expectedSize) {
	    Assertions.assertEquals(expectedSize,professor.getTeachings().size());
	 }
	  
	@SuppressWarnings("unused")
	private void expectedEvaluationsSize(Professor professore, int expectedSize) {
		Assertions.assertEquals(expectedSize,professor.getEvaluations().size());
	}
	  
}
