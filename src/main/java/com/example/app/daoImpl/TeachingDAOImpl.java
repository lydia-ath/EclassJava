package com.example.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.TeachingDAO;
import com.example.app.domain.Course;
import com.example.app.domain.Professor;
import com.example.app.domain.Teaching;

public class TeachingDAOImpl implements TeachingDAO{
	
private EntityManager em;
	
	public TeachingDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();
	}

	@Override
	public void save(Teaching teaching) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (teaching.getTeachingId() !=null) {
			em.merge(teaching);
		} else {
			em.persist(teaching);
		}
		
		tx.commit();
		
	}

	@Override
	public void delete(Teaching teaching) {
		System.out.println("");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teaching> findAll() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select t from Teaching t", Teaching.class );
		List<Teaching> teachings = q.getResultList();
		
		tx.commit();
		
		return teachings;
	}


	@Override
	public Professor getProfessor(Teaching teaching) {
		
		Professor professor = teaching.getProfessor();
		
		return professor;
	}

	@Override
	public Course getCourse(Teaching teaching) {
		
		Course course = teaching.getCourse();
		
		return course;
	}

}
