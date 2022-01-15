package com.example.app.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.domain.Evaluation;
import com.example.app.domain.Person;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;

/**
 * Value object for transferring students data over the wire ...
 *
 */

@XmlRootElement
public class StudentInfo extends Person{
		
		/** one student has many evaluations - one evaluation per teacher/course*/
		@OneToMany(mappedBy="evaluationId" ,cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Evaluation> evaluations = new ArrayList<>();
		
		/** many students belong to one  class*/
		@ManyToOne
		@JoinColumn(name="classId", nullable = true)
		private StudentClass studentclass;
		
		
		/** default constructor*/
		public StudentInfo() {
			super();
		}
		
		/** constructor*/
		public StudentInfo(String firstname, String lastname) {
			super(firstname, lastname);
			
		}
		
		/** getters*/
		@XmlTransient 
		public List<Evaluation> getEvaluations(){
			return evaluations;
		}
		
		
		/** setters temporarily empty*/
		public void setEvaluations(List<Evaluation> evaluations) {
			this.evaluations = evaluations;
		}
		
		public void setStudentPreciseClass(StudentClass studentClass) {
			this.studentclass = studentClass;
		}
		
		public void addEvalution(Evaluation evaluation) {
			
			this.evaluations.add(evaluation);
		}
		

		public void setStudentClass(StudentClass studentclass) {
	        this.studentclass = studentclass;
	    }
		
		@XmlTransient 
		public StudentClass getStudentClass() {
	        return studentclass;
	    }
		
		/**used for put rest services*/
		public Student getStudent(EntityManager em) {

			Student student = null;

			if (getPersonId() != null) {
				student = em.find(Student.class, getPersonId());
			} else {
				student = new Student();
			}

			student.setFirstname(getFirstname());
			student.setLastname(getLastname());
			

			return student;
		}
		
	}

