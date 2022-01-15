package com.example.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@Column(name="courseId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long courseId;
	
	@Column(name="courseName")
	private String courseName;
	
	@Column(name="courseHours")
	private String courseHours;
	
	@OneToOne(targetEntity=Teaching.class,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Teaching teaching; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@JoinColumn(name = "evaluationId", nullable = true)
	private List<Evaluation> evaluations = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
	@JoinColumn(name = "teachingId", nullable = true)
	private List<Teaching> teachings= new ArrayList<>();
	
	/** default constructor*/
	public Course() {
		
	}

	/** constructor*/
	public Course(String courseName, String courseHours) {
		this.courseName = courseName;
		this.courseHours = courseHours;
	}
	
	/** getters*/
	public Long getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseHours() {
		return courseHours;
	}
	
	public Teaching getTeaching() {
		return teaching;
	}
	
	/** setters*/
	public void setCourseId(Long id) {
		this.courseId = id;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseHours(String courseHours) {
		this.courseHours = courseHours;
	}

	public void setTeaching(Teaching teaching) {
		this.teaching = teaching;
	}
	
	public void addEvaluation(Evaluation evaluation) {
        if (evaluation != null) {
        	evaluation.setCourse(this); 
        	evaluations.add(evaluation);
        }
    }
	
	public void removeTeaching(Teaching teaching) {
        if (teaching != null) {
        	teaching.setCourse(null);
        }
    }
	
	public void addTeaching(Teaching teaching) {
			
			teachings.add(teaching);
			
		}
	
	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	
	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
	
	public List<Teaching> getTeachings() {
		
		return this.teachings;
	
	}	
}
