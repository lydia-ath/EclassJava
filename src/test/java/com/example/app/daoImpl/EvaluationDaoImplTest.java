package com.example.app.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.domain.Comments;
import com.example.app.domain.Course;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Professor;
import com.example.app.domain.Student;

public class EvaluationDaoImplTest {

private EvaluationDAO evaluationdao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		evaluationdao = new EvaluationDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<Evaluation> evaluations = evaluationdao.findAll();
		assertEquals(3, evaluations.size());
		
	}
	

	@Test
	  void deleteEvaluation() {
	      Evaluation evaluation = new Evaluation();
	      Course course = new Course("Kallitexnika", "2");
	      Student student = new Student();
	      evaluation.setComments(Comments.Excelent);
	      evaluation.setGrade(20);
	      evaluation.setCourse(course);
	      evaluation.setStudent(student);
	      evaluationdao.delete(evaluation);
	      List<Evaluation> evaluations = evaluationdao.findAll();

	      assertFalse(evaluations.contains(evaluation));	
	      
	  }
}

