package com.example.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.JPAUtil;
import com.example.app.domain.Course;
import com.example.app.domain.Teaching;

public class CourseDaoImpl implements CourseDAO{
	
	private EntityManager em;
	
	public CourseDaoImpl() {
		
		em = JPAUtil.getCurrentEntityManager();
	}

	@Override
	public void save(Course course) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (course.getCourseId() !=null) {
			em.merge(course);
		} else {
			em.persist(course);
		}
		
		tx.commit();
		//em.close();
	}

	
	@Override
	public List<Course> findAll(EntityManager em) {
		
	      List<Course> courses = em.createQuery("select course from Course course",Course.class).getResultList();
	      em.close();
		
		return courses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select c from Course c", Course.class );
		List<Course> courses = q.getResultList();
		
		tx.commit();
		
		return courses;


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findCourseName(String coursename) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery(
				"select c from Course c "
				+ " where c.courseName = :coursename");
		q.setParameter("coursename", coursename);
		List<Course> courses = q.getResultList();
		
		tx.commit();
		return courses;
	}
	
	@Override
	public List<Teaching> findCourseTeachings(String course) {
		  EntityTransaction tx = em.getTransaction(); 
		  tx.begin();
	  
		  Query q = em.createQuery( "select t from Teaching t " +
				  " where t.course.courseName like :course"); 
		  q.setParameter("course", course);
		  List<Teaching> teachings = q.getResultList();
	  
		  tx.commit(); 
		  return teachings;
	}
}
