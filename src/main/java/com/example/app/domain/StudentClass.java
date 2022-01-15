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
@Table(name="studentClasses")
public class StudentClass {
	
	@Id
	@Column(name="classId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentClassId;
	
	/** όνομα τμήματος*/
	@Column(name="name")
	private String className;
	
	/** όνομα τάξης*/
	@Column(name="preciseCLass")
	private Classes classes;
	
	/** one student belongs to one  class*/
	@OneToMany
	@JoinColumn(name="studentId", nullable = true)
	private List<Student> students = new ArrayList<>();
	
	@OneToOne(targetEntity=Teaching.class,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Teaching teaching;
	
	@OneToMany
	@JoinColumn(name="teachings", nullable = true)
	private List<Teaching> teachings = new ArrayList<>();
	
	
	
	/** Default constructor*/
	public StudentClass() {
		
	}
	
	/** Constructor*/
	public StudentClass(String className) {
		this.className = className;
	}
	
	/** Getters*/
	public Long getStudentClassId() {
		return studentClassId;
	}
	
	public String getClassName() {
		return className;
	}
	public Classes getClasses() {
		return classes;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public Teaching getTeaching() {
		return teaching;
	}

	/** Setters*/
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setTeaching(Teaching teaching) {
		this.teaching = teaching;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void addStudent(Student student) {
		
		students.add(student);	
		
	}
	
	public List<Teaching> getTeachings() {
		return teachings;
	}
	
	public void addTeaching(Teaching teaching) {
		teachings.add(teaching);
		
	}
	
	public void removeStudent(Student student) {
		students.remove(student);	
	}
	
	public void removeTeaching(Teaching teaching) {
		if(teaching != null) {
			teaching.setStudentclass(null);
		}
		
	}
	
}
