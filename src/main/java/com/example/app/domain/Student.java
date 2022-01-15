package com.example.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("student")
public class Student extends Person{
	
	/** one student has many evaluations - one evaluation per teacher/course*/
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "evaluationId", nullable = true)
	private List<Evaluation> evaluations = new ArrayList<>();
	
	/** many students belong to one  class*/
	@ManyToOne
	@JoinColumn(name="classId", nullable = true)
	private StudentClass studentclass;
	
	
	/** default constructor*/
	public Student() {
		super();
	}
	
	/** constructor*/
	public Student(String firstname, String lastname) {
		super(firstname, lastname);
		
	}
	
	/** getters*/
	@XmlTransient 
	public List<Evaluation> getEvaluations(){
		return evaluations;
	}
	
	@XmlTransient 
	public StudentClass getStudentClass() {
        return studentclass;
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
	
	
	public void removeStudentFromClass(StudentClass studentClass) {
		if(studentclass.getStudents()!= null) {
			studentclass.removeStudent(this);
		}
	}
}
