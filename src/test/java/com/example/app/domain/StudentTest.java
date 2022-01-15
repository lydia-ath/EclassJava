package com.example.app.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {
	
	@Test
    public void succefulSetUp() {
		Student student = new Student("Aggelos", "Athanasiou");
        expectedStudentsSize(student,1);
    }
	
	private void expectedStudentsSize(Student student, int expectedSize) {
		Assertions.assertEquals("Aggelos", student.getFirstname());
		Assertions.assertEquals("Athanasiou", student.getLastname());
		
	}

	@Test
    public void addNullForStudentclass() {
		Student student = new Student();
		student.setStudentClass(null);
        Assertions.assertEquals(null,student.getStudentClass());  
    }

	
	@Test
    public void addStudentClass() {
		Student student = new Student();
		StudentClass studentclass = new StudentClass();
		studentclass.setClassName("a1");
		student.setStudentClass(studentclass);
        Assertions.assertEquals("a1", student.getStudentClass().getClassName());     
    }
	
	@Test
    public void removeStudentClass() {
		Student student = new Student();
		StudentClass studentclass = new StudentClass();
		studentclass.setClassName("a1");
		student.setStudentClass(studentclass);
        student.removeStudentFromClass(studentclass);
        Assertions.assertEquals(0, studentclass.getStudents().size());
    }
	
	@Test
    public void addStudentEvaluation() {
		Student student = new Student();
		Evaluation evaluation = new Evaluation();
		evaluation.setStudent(student);
		student.addEvalution(evaluation);
        Assertions.assertEquals(1, student.getEvaluations().size());     
    }
	
	
	private void EvaluationBidirectionalAssociationInvariant(Student student) {
		for(Evaluation evaluation: student.getEvaluations()) {
        	Assertions.assertTrue(evaluation.getStudent().getEvaluations().contains(evaluation));            
        }
		
	}

	@Test
    public void addNullAsEvaluation() {
		Student student = new Student();
		student.addEvalution(null);
        expectedEvaluationsSize(student,1);
    }

	private void expectedEvaluationsSize(Student student, int expectedSize) {
		Assertions.assertTrue(student.getEvaluations().contains(null));
		
	}
	
	@Test
    public void addStudenClass() {
		Student student = new Student();
		student.setStudentClass(null);
        expectedStudentClass(student,1);
    }

	private void expectedStudentClass(Student student, int expectedSize) {
		Assertions.assertEquals(null, student.getStudentClass());
	}

	

	@Test
    public void checkAdress() {
		Student student = new Student();
		Address address = new Address();
		address.setCity("Μαρούσι");
		address.setCountry("Greece");
		address.setStreet("Ηροδότου");
		address.setNumber(17);
		address.setZipcode("19015");
		student.setAddress(address);
        Assertions.assertEquals("Μαρούσι", student.getAddress().getCity());      
        Assertions.assertEquals("Greece", student.getAddress().getCountry());  
        Assertions.assertEquals("Ηροδότου", student.getAddress().getStreet() ); 
        Assertions.assertEquals(17, student.getAddress().getNumber()); 
        Assertions.assertNotEquals("15017", student.getAddress().getZipcode()); 
    }
	
	@Test
	public void findbyFirstname() {
		Student student = new Student();
		student.setFirstname("lydia");
		Assertions.assertEquals("lydia", student.getFirstname()); 
	}
	
	@Test
	public void findbyLastname() {
		Student student = new Student();
		student.setLastname("lydia");
		Assertions.assertEquals("lydia", student.getLastname()); 
	}
	
}

