package com.example.app.resource;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.domain.Comments;
import com.example.app.domain.Course;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Professor;
import com.example.app.domain.Secretariat;
import com.example.app.domain.Student;

/**
 * Value object for transferring evaluation data over the wire ...
 *
 */

@XmlRootElement
public class EvaluationInfo implements Serializable{

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
	public EvaluationInfo() {
		
	}

	/**Constructor*/
	public EvaluationInfo(int grade, Comments comments) {
		this.grade = grade;
		this.comments = comments;
	}
	
	/**getters*/
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
	
	public Course getCourse() {
		return course;
	}
	
	/**setters*/
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	/**used for put and delete rest services*/
	public Evaluation getEvaluation(EntityManager em) {
		
		Evaluation evaluation = null;

		if (getEvaluationId() != null) {
			evaluation = em.find(Evaluation.class, getEvaluationId());
		} else {
			evaluation = new Evaluation();
		}
		
		evaluation.setCourse(getCourse());
		evaluation.setStudent(getStudent());
		evaluation.setGrade(getGrade());
		evaluation.setComments(getComments());
		em.close();

		return evaluation;
	}
}

