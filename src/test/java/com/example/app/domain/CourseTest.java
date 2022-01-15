package com.example.app.domain;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.Initializer;
import com.example.app.daoImpl.CourseDaoImpl;

public class CourseTest {
		Course course;
		Evaluation evaluation;
		Teaching teaching;
	    
	    @BeforeEach
	    public void setUp() {
	    	course = new Course();
	    	evaluation = new Evaluation();
	    	teaching =new Teaching();
	    	course.addTeaching(teaching);
	    	course.addEvaluation(evaluation);
	    	teaching.setCourse(course);
	    }
	
	  @Test
	    public void succefulSetUp() {
	        teachingBidirectionalAssociationInvariant(course);
	        successfullAdditionofTeaching(course, teaching, 1);
	        
	        EvaluationBidirectionalAssociationInvariant(course);
	        expectedEvaluationSize(course,1);
	        successfullAdditionofEvaluation(course, evaluation);
	    }
	
	private void successfullAdditionofEvaluation(Course course, Evaluation evaluation) {
		Assertions.assertTrue(course.getEvaluations().contains(evaluation));
    	Assertions.assertSame(course, evaluation.getCourse());
			
	}

	private void successfullAdditionofTeaching(Course course, Teaching teaching, int expected) {
		Assertions.assertSame(course, teaching.getCourse());
			
		}

	private void expectedEvaluationSize(Course course, int expectedSize) {
		Assertions.assertEquals(expectedSize, course.getEvaluations().size());
			
		}

	private void EvaluationBidirectionalAssociationInvariant(Course course) {
		for(Evaluation evaluation : course.getEvaluations()) {
        	Assertions.assertSame(course, evaluation.getCourse());
        }
			
	}

	private void teachingBidirectionalAssociationInvariant(Course course) {
        	Assertions.assertSame(course, teaching.getCourse());
			
	}
	
	@Test
    public void addNullAsTeaching() {
		course.addTeaching(null);
		Assertions.assertEquals(null, course.getTeaching());
        teachingBidirectionalAssociationInvariant(course);
    }

	 @Test
	    public void addNullAsEvaluation() {
		 	course.addEvaluation(null);
	        Assertions.assertNotEquals(null, course.getEvaluations());
	        EvaluationBidirectionalAssociationInvariant(course);
	    }
	 
	@Test
	    public void addTeaching() {        
		 	teaching = new Teaching();
		 	course = new Course();
		 	course.addTeaching(teaching);
		 	teaching.setCourse(course);
	        successfullAdditionofTeaching(course,teaching, 0);
	        teachingBidirectionalAssociationInvariant(course);
	    }
	 
	 @Test
	    public void addEvaluation() {        
	        expectedEvaluationSize(course,1);    
	        evaluation = new Evaluation();
	        course.addEvaluation(evaluation);
	        successfullAdditionofEvaluation(course,evaluation);
	        expectedEvaluationSize(course,2);
	        EvaluationBidirectionalAssociationInvariant(course);
	    }

	 @Test
	    public void removeNullAsTeaching() {
	        course.removeTeaching(null);
	        teachingBidirectionalAssociationInvariant(course);        
	    }
	 
	 @Test
	    public void removeTeaching() {
	        teachingBidirectionalAssociationInvariant(course);
	        course.removeTeaching(teaching);
	        successfullRemovalofTeaching(course, teaching);
	        expectedTeachingSize(course,0);        
	    }
	 
	
	 @Test
	    public void removeEvaluation() {
		 	Evaluation evaluation1 = new Evaluation();
		 	course.addEvaluation(evaluation1);
		 	//evaluation1.addCourse(course);
		 	EvaluationBidirectionalAssociationInvariant(course);
	        Assertions.assertEquals(2, course.getEvaluations().size());
	        //course.removeEvaluation(evaluation);
	        EvaluationBidirectionalAssociationInvariant(course);
	        Assertions.assertEquals(2, course.getEvaluations().size());
	    }
	 
	 
	private void expectedTeachingSize(Course course, int expectedSize) {
		Assertions.assertEquals(null, course.getTeaching());
		
	}
	private void successfullRemovalofTeaching(Course course, Teaching teaching) {
		Assertions.assertNull(teaching.getCourse());
		
	}

	@Test
	void  findCourseName() {
		Course course = new Course();
		course.setCourseName("maths");
		assertEquals("maths", course.getCourseName());
	}
	
	@Test
	void  findCourseHours() {
		Course course = new Course();
		course.setCourseHours("13");
		assertNotEquals("16", course.getCourseHours());
	}

}
