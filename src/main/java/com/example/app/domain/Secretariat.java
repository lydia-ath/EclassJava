package com.example.app.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.dao.SecretariatDAO;
import com.example.app.dao.StudentClassDAO;
import com.example.app.dao.StudentDAO;
import com.example.app.dao.TeachingDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.ScheduleSlotDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.daoImpl.TeachingDAOImpl;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

@Entity
@DiscriminatorValue("secretariat")
public class Secretariat extends User{
	
	static HashMap<String, Integer> classALessons = new HashMap<String, Integer>();
	static HashMap<String, Integer> classBLessons = new HashMap<String, Integer>();
	static HashMap<String, Integer> classCLessons = new HashMap<String, Integer>();
	
	/** Default Constructor*/
	public Secretariat() {
		
	}
	
	/** Constructor*/
	public Secretariat(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
	}
	
	/** getters and setters can be defined in superclasses*/

	public HashMap<String, Integer> getClassALessons() {
		return classALessons;
	}

	public void setClassALessons(HashMap<String, Integer> classALessons) {
		Secretariat.classALessons = classALessons;
	}
	
	public List<StudentClass> getStudentClasses(){
		
		StudentClassDAO studentclassdao = new StudentClassDAOImpl();
		List<StudentClass> studentclasses = studentclassdao.findAll();
		
		return studentclasses;
		
	}
	
}

