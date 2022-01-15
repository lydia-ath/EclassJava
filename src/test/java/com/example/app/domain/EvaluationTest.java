package com.example.app.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;

public class EvaluationTest {
	
	Evaluation evaluation;
	Professor professor;
	Course course;
	Student student;
	
	@BeforeEach 
	public void setup() {
		evaluation = new Evaluation();
		professor = new Professor("SOFIA", "DROUGKA","sofi", "1234", 25);
		course = new Course("History", "4");
		student = new Student("Nikos", "Aggelakis");
		//Initializer initializer = new Initializer();
	}

	
	@Test
	void addEvaluation() {
		
		evaluation.setGrade(8);
		professor.completesEvaluation(evaluation);
		assertEquals(1, professor.getEvaluations().size());
		
	}
	
	@Test
	void removeEvaluation() {
		professor.removeEvaluation(evaluation);
		expectedEvaluationsSize(professor,0);		  
	}
	
	@Test
	void verifyGrade() {
		
		evaluation.setGrade(8);
		assertEquals(8, evaluation.getGrade());
	}
	
	@Test
	void verifyCourse() {
		evaluation.setCourse(course);
		assertTrue(evaluation.getCourse()!=null);
	}
	

	@Test
	void verifyStudent (){
		evaluation.setStudent(student);
		assertTrue(evaluation.getStudent()!=null);
	}
	
	@Test
	void evaluationSetUp() {
		course.addEvaluation(evaluation);
		correctAdditionofEvaluation(course, evaluation);
	}
	
	@AfterEach
	public void cleanUpEach(){
		professor.removeEvaluation(evaluation);
		System.out.println("Data base will be intialized..");
		
	}

	
	@SuppressWarnings("unused")
	private void expectedEvaluationsSize(Professor professore, int expectedSize) {
		Assertions.assertEquals(expectedSize,professor.getEvaluations().size());
	}
	
	private void correctAdditionofEvaluation(Course course, Evaluation evaluation) {
		Assertions.assertTrue(course.getEvaluations().contains(evaluation));
    	Assertions.assertSame(course, evaluation.getCourse());
			
	}
}
