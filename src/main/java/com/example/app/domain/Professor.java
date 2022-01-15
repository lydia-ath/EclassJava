package com.example.app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.StudentClassDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.daoImpl.StudentClassDAOImpl;

@Entity
@DiscriminatorValue("professor")
public class Professor extends User {

	@Column(name = "hours_of_employment")
	private int hoursOfEmployement;

	@Column(name = "specialty")
	private String specialty;

	@OneToMany(mappedBy = "professor", fetch=FetchType.EAGER)
	@XmlTransient 
	private List<Teaching> teachings = new ArrayList<>();
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	@JoinColumn(name = "evaluationId")
	@XmlTransient 
	private List<Evaluation> evaluations = new ArrayList<>();
	
	/** default constructor */
	public Professor() {

	}

	/** Constructor */
	public Professor(String firstname, String lastname, String username, String password, int hoursOfEmployement) {
		super(firstname, lastname, username, password);
		// this.id = id;
		this.hoursOfEmployement = hoursOfEmployement;
	}

	/** getters */
	public int getHoursOfEmployement() {
		return hoursOfEmployement;
	}

	@XmlTransient 
	public List<Teaching> getTeachings() {
		return teachings;
	}

	/** setters */
	public void setHoursOfEmployement(int hoursOfEmployement) {
		this.hoursOfEmployement = hoursOfEmployement;
	}

	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}

	public void addTeaching(Teaching teaching) {

		teachings.add(teaching);
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	};

	public void removeTeaching(Teaching teaching) {

		teachings.remove(teaching);
	}
	
	public void completesEvaluation(Evaluation evaluation) {
		
		evaluations.add(evaluation);
	}
	
	
	public List<Evaluation> getEvaluations(){
		
		return this.evaluations;
	}

	public void removeEvaluation(Evaluation evaluation) {
	
		this.evaluations.remove(evaluation);
	}
	
}
