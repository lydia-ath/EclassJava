package com.example.app.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StudentClassTest {

	StudentClass studentclass;
	Teaching teaching;
	Student student;
	List<Student> students = new ArrayList<>();
	
	
	 @BeforeEach
	 public void setUp() {
		 studentclass = new StudentClass("A Γυμνασίου");
		 teaching = new Teaching(4);
		 student = new Student("Lydia", "Athanasiou");
		 studentclass.setTeaching(teaching);
		 studentclass.addStudent(student);
		 studentclass.setStudents(students);
	 }
	 
	 @Test
	    public void succefulSetUp() {
	        studentBidirectionalAssociationInvariant(studentclass);
	        studentclass.setClasses(Classes.A_class);
	        Assertions.assertEquals(Classes.A_class, studentclass.getClasses());
	    }
	 
	 @Test
	 public void checkClassName() {
		 studentclass.setClassName("A1");
		 Assertions.assertEquals("A1", studentclass.getClassName());
	 }
	 
	 @Test
	 public void checkClassEnum() {
		 studentclass.setClasses(Classes.B_class);
	        Assertions.assertEquals(Classes.B_class, studentclass.getClasses());
	 }
	 
	 @Test
	    public void addStudent() {        
	        expectedStudentSize(studentclass,0); 
	        Student student = new Student();
	        studentclass.addStudent(student);
	        successfullAdditionofStudent(studentclass, student);
	        expectedStudentSize(studentclass,1);
	    }
	 @Test
	    public void addTeaching() {        
	        expectedTeachingSize(studentclass,0); 
	        Teaching teaching = new Teaching();
	        studentclass.addTeaching(teaching);
	        successfullAdditionofTeaching(studentclass, teaching);
	        expectedTeachingSize(studentclass,1);
	    }
	 
	 @Test
	    public void removeStudent() {
	        studentBidirectionalAssociationInvariant(studentclass);
	        expectedStudentSize(studentclass,0); 
	        Student student = new Student();
	        studentclass.addStudent(student);
	        successfullAdditionofStudent(studentclass, student);
	        expectedStudentSize(studentclass,1);
	        studentclass.removeStudent(student);
	        successfullRemovalofStudent(studentclass,student);
	        expectedStudentSize(studentclass,0);        
	        studentBidirectionalAssociationInvariant(studentclass);
	    }
	 
	 
	 @Test
	    public void updateTeaching() {
	        teachingBidirectionalAssociationInvariant(studentclass);
	        expectedTeachingSize(studentclass,0); 
	        Teaching teaching = new Teaching();
	        studentclass.addTeaching(teaching);
	        successfullAdditionofTeaching(studentclass, teaching);
	        expectedTeachingSize(studentclass,1);
	        studentclass.removeTeaching(teaching);
	        successfullRemovalofTeaching(studentclass,teaching);
	        Assertions.assertSame(null, teaching.getStudentclass());   
	    }

	private void successfullRemovalofTeaching(StudentClass studentclass, Teaching teaching) {
		Assertions.assertTrue(studentclass.getTeachings().contains(teaching));
		
	}

	private void teachingBidirectionalAssociationInvariant(StudentClass studentclass) {
		for(Teaching teaching : studentclass.getTeachings()) {
        	Assertions.assertSame(studentclass, teaching.getStudentclass());
        }
		
	}

	private void successfullRemovalofStudent(StudentClass studentclass, Student student) {
		Assertions.assertFalse(studentclass.getStudents().contains(student));
		
	}

	private void successfullAdditionofTeaching(StudentClass studentclass, Teaching teaching) {
		Assertions.assertTrue(studentclass.getTeachings().contains(teaching));
		
	}

	private void expectedTeachingSize(StudentClass studentclass, int expectedSize) {
		Assertions.assertEquals(expectedSize, studentclass.getTeachings().size());
		
	}

	private void expectedStudentSize(StudentClass studentclass, int expectedSize) {
		Assertions.assertEquals(expectedSize, studentclass.getStudents().size());
		
	}

	private void successfullAdditionofStudent(StudentClass studentclass, Student student) {
		Assertions.assertTrue(studentclass.getStudents().contains(student));
	
	}

	private void studentBidirectionalAssociationInvariant(StudentClass studentclass) {
		 for(Student student : studentclass.getStudents()) {
	        	Assertions.assertSame(studentclass, student.getClass());
	        }
		
	}
}

