package com.example.app.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentClassDAO;
import com.example.app.domain.Professor;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;

public class StudentClassDAOImpl implements StudentClassDAO{


	@Inject
	private EntityManager em;
	protected static List<StudentClass> studentClasses = new ArrayList<StudentClass>();
	
	public StudentClassDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}

	@Override
	public void save(StudentClass studentClass) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (studentClass.getStudentClassId() !=null) {
			em.merge(studentClass);
		} else {
			em.persist(studentClass);
		}
		
		tx.commit();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentClass> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select sc from StudentClass sc", StudentClass.class );
		List<StudentClass> studentClassess = q.getResultList();
		
		tx.commit();
		
		return studentClassess;
		
	}
	

	@Override
	public void save1(StudentClass student) {
		if (! studentClasses.contains(student)) {
			 studentClasses.add(student);    
        }
		
	}

	@Override
	public boolean findByClassName(String className) {
		  EntityTransaction tx = em.getTransaction(); tx.begin();
		  
		  Query q = em.createQuery( "select sc from StudentClass sc"); 
		 
		  List<StudentClass> studentclasses = q.getResultList();
		  	  
		  tx.commit();
		  
		  for(StudentClass studentclass :studentclasses) {
			  
			  if(studentclass.getClassName().equals(className)) {
				  
				  return true;
			  }
		  }
		  
		  em.close();
		  
		  return false;
	}


}
