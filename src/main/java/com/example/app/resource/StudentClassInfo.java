package com.example.app.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.domain.Classes;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;

/**
 * Value object for transferring student class data over the wire ...
 *
 */

@XmlRootElement
public class StudentClassInfo {

	
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
	@OneToMany(mappedBy="studentId" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();
	
	@OneToOne(targetEntity=Teaching.class,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Teaching teaching;
	
	@OneToMany(mappedBy="teachingId" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teaching> teachings = new ArrayList<>();
	
	
	
	/** Default constructor*/
	public StudentClassInfo() {
		
	}
	
	/** Constructor*/
	public StudentClassInfo(String className) {
		this.className = className;
	}
	
	/**Getters*/
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

	/**Setters*/
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
	
	/**used for put rest services*/
	public StudentClass getStudentClass(EntityManager em) {
		
		StudentClass studentclass = null;

		if (getStudentClassId()!= null) {
			studentclass = em.find(StudentClass.class, getStudentClassId());
		} else {
			studentclass = new StudentClass();
		}

		studentclass.setClassName(getClassName());
		em.close();

		return studentclass;
	}
}

