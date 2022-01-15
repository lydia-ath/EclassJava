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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="teachings")
public class Teaching {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teachingId;
	
	@Column(name="teachingHours")
	private int teachingHours;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="teaching", nullable = true)
	@XmlTransient 
	private Professor professor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseId", nullable = true)
	@XmlTransient 
	private Course course;
	
	@OneToOne(targetEntity=StudentClass.class, fetch = FetchType.EAGER)
	private StudentClass studentclass;
	
	@OneToMany(mappedBy = "teaching", cascade = CascadeType.ALL)
	@XmlTransient 
	private List<ScheduleSlot> scheduleSlots = new ArrayList<>();
	
	/** default constructor*/
	public Teaching() {
		
	}

	/** constructor*/
	public Teaching(int teachingHours) {
	
		this.teachingHours = teachingHours;
	}
	
	/** getters*/
	public Long getTeachingId() {
		return teachingId;
	}

	public int getTeachingHours() {
		return teachingHours;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public Course getCourse() {
		return course;
	}

	public StudentClass getStudentclass() {
		return studentclass;
		
	}
	
	/** setters*/
	public void setTeachingHours(int teachingHours) {
		this.teachingHours = teachingHours;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@XmlTransient 
	public List<ScheduleSlot> getScheduleSlots() {
		return scheduleSlots;
	}

	public void setScheduleSlots(List<ScheduleSlot> scheduleSlots) {
		this.scheduleSlots = scheduleSlots;
	}

	public void setStudentclass(StudentClass sc1) {
		this.studentclass = sc1;
		
	}

	public void addScheduleSlot(ScheduleSlot scheduleslot) {
		this.scheduleSlots.add(scheduleslot);
	}	
	
	public void deleteScheduleSlot(ScheduleSlot scheduleslot) {
		if (scheduleslot != null) {
			this.scheduleSlots.remove(scheduleslot);
			
		}
    }
	
	public void deleteProfessor() {
		
		this.professor = null;
	}
	
	public void deleteCourse() {
		
		this.course = null;
	}
	
	
}
