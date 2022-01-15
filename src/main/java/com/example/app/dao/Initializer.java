package com.example.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.example.app.dao.JPAUtil;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.ScheduleSlotDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.daoImpl.TeachingDAOImpl;
import com.example.app.domain.Classes;
import com.example.app.domain.Course;
import com.example.app.domain.Days;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Hours;
import com.example.app.domain.Professor;
import com.example.app.domain.ScheduleSlot;
import com.example.app.domain.Secretariat;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;
import com.example.app.domain.User;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

public class Initializer {
	
	private EntityManager em;
	List<Professor> professors = new ArrayList<>();

	public Initializer() {
		
		this.em = JPAUtil.getCurrentEntityManager();
	}
	
	private void eraseData() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createNativeQuery("delete from scheduleslots").executeUpdate();
		em.createNativeQuery("delete from teachings").executeUpdate();	
		em.createNativeQuery("delete from evaluations").executeUpdate();
		em.createNativeQuery("delete from courses").executeUpdate();
		em.createNativeQuery("delete from persons").executeUpdate();
		
		tx.commit();
	}
	
	public void prepareData() {
		
		eraseData();
		
		/*Add students to classes & precise classes*/
		StudentClass sc1 = new StudentClass();
		sc1.setClasses(Classes.A_class);
		sc1.setClassName("A1");
		StudentClass sc2 = new StudentClass();
		sc2.setClasses(Classes.A_class);
		sc2.setClassName("A2");
		StudentClass sc3 = new StudentClass();
		sc3.setClasses(Classes.B_class);
		sc3.setClassName("B1");
		StudentClass sc4 = new StudentClass();
		sc4.setClasses(Classes.C_class);
		sc4.setClassName("Γ1");
		StudentClass sc5 = new StudentClass();
		sc1.setClasses(Classes.C_class);
		sc5.setClassName("Γ2");
		
		/*initialization of courses objects*/
		Course course1 = new Course("PHYSICS", "5");
		Course course2 = new Course("MATHEMATICS", "6");
		Course course3 = new Course("INFORMATICS", "4");
		
		CourseDAO cs = new CourseDaoImpl();
		cs.save(course1);
		cs.save(course2);
		cs.save(course3);
		
		Student st1 = new Student("Athanasia", "Athanasiou");
		Student st2  = new Student("Vaso", "Athanasiou");	
		Student st3  = new Student("kiki", "Athanasiou");	
		Student st4  = new Student("George", "Papadakis");	
		Student st5  = new Student("Yannis", "Papadopoulos");	
		Student st6  = new Student("Yannis", "Papadopoulos");	
		Student st7  = new Student("Yiota", "Papadopoulou");	
		Student st8  = new Student("Kostas", "Dielas");	
		
		StudentDAO st = new StudentDAOImpl();
		st.save(st1);
		st.save(st2);
		st.save(st3);
		st.save(st4);
		st.save(st5);
		st.save(st6);
		st.save(st7);
		st.save(st8);
		
		sc1.addStudent(st1);
		sc1.addStudent(st2);
		sc1.addStudent(st3);
		sc1.addStudent(st4);
		sc1.addStudent(st5);
		sc1.addStudent(st6);
		sc1.addStudent(st7);
		
		StudentClassDAO sc= new StudentClassDAOImpl();
		sc.save(sc1);
		sc.save(sc2);
		sc.save(sc3);
		sc.save(sc4);
		sc.save(sc5);
		
		Teaching t1 = new Teaching();
		t1.setStudentclass(sc1);
		
		Teaching t2 = new Teaching();
		t2.setStudentclass(sc2);
		
		Teaching t3 = new Teaching();
		t3.setStudentclass(sc3);
		
		Teaching t4 = new Teaching();
		t4.setStudentclass(sc4);
		
		Teaching t5 = new Teaching();
		t5.setStudentclass(sc5);
		
		Professor prof1 = new Professor("SOFIA", "DROUGKA","sofie", "1234", 19);
		this.professors.add(prof1);
		//prof1.studentEvaluation();
		User userprof = new Professor();
		
		Secretariat sec1 = new Secretariat("Κυριακή", "Βασιλείου", "kiriaki", "1234");
		Secretariat sec2 = new Secretariat("Παρασκεύη", "Ζαχαριάδη", "friday", "1234");
		Secretariat secretariat = new Secretariat("MARIA", "ΤΖΑΝΑΚΑΚΗ", "martz", "1234");
		SecretariatDAO secretariatdao = new SecretariatDAOImpl();
		
		secretariatdao.save1(sec1);
		secretariatdao.save1(sec2);
		secretariatdao.save1(secretariat);
		
		User usersec = new Secretariat();
		
		prof1.addTeaching(t1);
		prof1.addTeaching(t2);
		prof1.addTeaching(t3);
		prof1.addTeaching(t4);
		prof1.addTeaching(t5);
		ProfessorDAO prd = new ProfessorDAOImpl();
		prd.save(prof1);
		
		ScheduleSlot scheduleslot1 = new ScheduleSlot();
		ScheduleSlotDAO scheduledao = new ScheduleSlotDAOImpl();
		scheduleslot1.setDays(Days.Friday);
		scheduleslot1.setHours(Hours.forth_Hour);
		scheduleslot1.setTeaching(t1);
		scheduledao.save(scheduleslot1);
		
		t1.setProfessor(prof1);
		t2.setProfessor(prof1);
		t3.setProfessor(prof1);
		t4.setProfessor(prof1);
		t5.setProfessor(prof1);
		
		t1.addScheduleSlot(scheduleslot1);
		
		
		/*add 2 teachings for course1 */
		t1.setCourse(course1);
		t2.setCourse(course1);
		//////
		t3.setCourse(course3);
		t4.setCourse(course2);
		t5.setCourse(course1);
			
		TeachingDAO teachingdao = new TeachingDAOImpl();
		teachingdao.save(t1);
		teachingdao.save(t2);
		teachingdao.save(t3);
		teachingdao.save(t4);
		teachingdao.save(t5);
		
		Evaluation eval1 = new Evaluation();
		eval1.setCourse(course1);
		eval1.setGrade(18);
		eval1.setStudent(st1);
		Evaluation eval2 = new Evaluation();
		eval2.setCourse(course2);
		eval2.setGrade(15);
		eval2.setStudent(st2);
		
		Evaluation eval3 = new Evaluation();
		eval3.setCourse(course3);
		eval3.setGrade(7);
		eval3.setStudent(st3);
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();	
		evaluationdao.save(eval1);
		evaluationdao.save(eval2);
		evaluationdao.save(eval3);
		
		st1.addEvalution(eval1);
		st1.addEvalution(eval2);
		st1.addEvalution(eval3);
		
		st.save(st1);
		
		/*ScheduleSlot scheduleslot1 = new ScheduleSlot();
		ScheduleSlotDAO scheduledao = new ScheduleSlotDAOImpl();
		scheduleslot1.setDays(Days.Friday);
		scheduleslot1.setHours(Hours.forth_Hour);
		scheduleslot1.setTeaching(t1);
		scheduledao.save(scheduleslot1);*/
				
		em.close();
		
	}
	
	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public CourseDAO getCourseDAO() {
		
		return new CourseDaoImpl();
	}
	
	public ProfessorDAO getProfessorDAO() {
		
		return new ProfessorDAOImpl();
	}

	public EvaluationDAO getEvaluationDAO() {
		
		return new EvaluationDAOImpl();
	}
	
	public SecretariatDAO getSecretariatDAO() {
		
		return new SecretariatDAOImpl();
	}
	
	public StudentDAO getStudentDAO() {
		
		return new StudentDAOImpl();
	}
	
	public StudentClassDAO getStudentClassDAO() {
		
		return new StudentClassDAOImpl();
	}
	
	public ScheduleSlotDAO getScheduleSlotDAO() {
		
		return new ScheduleSlotDAOImpl();
	}
	
public TeachingDAO getTeachingDAO() {
		
		return new TeachingDAOImpl();
	}
}
