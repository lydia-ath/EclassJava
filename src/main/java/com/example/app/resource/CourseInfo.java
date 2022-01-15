package com.example.app.resource;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.domain.Evaluation;
import com.example.app.domain.Teaching;

/**
 * Value object for transferring course data over the wire ...
 *
 */

@XmlRootElement
public class CourseInfo {

	private Long courseId;

	private String courseName;

	private String courseHours;
	
	private Teaching teaching; 
	
	private List<Evaluation> evaluations = new ArrayList<>();
	
	private List<Teaching> teachings= new ArrayList<>();

	/** default constructor*/
	public CourseInfo() {
		
	}

	/** constructor*/
	public CourseInfo(String courseName, String courseHours) {
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