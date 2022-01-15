package com.example.app.domain;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="evaluationIdId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long evaluationId;
	
	@Column(name="grade")
	private int grade;
	
	@Column(name="comments")
	private Comments comments;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="courseId", nullable = true)
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="personId", nullable = true)
	private Professor professor;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="studentId", nullable = true)
	private Student student;
	
	
	/** default constructor*/
	public Evaluation() {
		
	}
	
	/**Constructor*/
	public Evaluation(int grade, Comments comments) {
		this.grade = grade;
		this.comments = comments;
	}
	
	/**Getters*/
	public Long getEvaluationId() {
		return evaluationId;
	}

	public int getGrade() {
		return grade;
	}

	public Student getStudent() {
		return student;
	}
	
	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}
	
	/**setters*/
	public void setGrade(int grade) {
		this.grade = grade;
	}

	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	/*public void removeCourse(Course course) {
		if (course != null) {
			this.courses.remove(this.course);
			
		}
    }*/
	
	/*public void addCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
        }
    }*/
	
}
