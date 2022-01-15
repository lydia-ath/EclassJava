package com.example.app.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentDAO;
import com.example.app.domain.Professor;
import com.example.app.domain.Student;

public class StudentDAOImpl implements StudentDAO{
	
	private EntityManager em;
	protected static List<Student> students = new ArrayList<Student>();

	public StudentDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}


	@Override
	public void save(Student student) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (student.getPersonId() !=null) {
			em.merge(student);
		} else {
			em.persist(student);
		}
		
		tx.commit();
	}

	/** new method by L*/
	@Override
	public void save1(Student student) {
        if (! students.contains(student)) {
        	students.add(student);    
        }        
    }
	
	@Override
	public void delete(Student student) {
		if (! students.contains(student)) {
			students.remove(student);    
        } 	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select st from Student st", Student.class);
		List<Student> students= q.getResultList();
		
		tx.commit();
		
		return students;
	}


	@Override
	public Student find(long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByStudentFirstName(String firstName) {
		
		  EntityTransaction tx = em.getTransaction(); tx.begin();
		  Query q = em.createQuery( "select st from Student st"); 
		 
		  List<Student> students = q.getResultList();
		  	  
		  tx.commit();
		  
		  for(Student student :students) {
			  
			  if(student.getFirstname().equals(firstName)) {
				  
				  return true;
			  }
		  }
		  
		  em.close();
		  
		  return false;
	}

	@Override
	public boolean findByStudentLastName(String lastName) {

		  EntityTransaction tx = em.getTransaction(); tx.begin();
		  Query q = em.createQuery( "select st from Student st"); 
		 
		  List<Student> students = q.getResultList();
		  	  
		  tx.commit();
		  
		  for(Student student :students) {
			  
			  if(student.getLastname().equals(lastName)) {
				  
				  return true;
			  }
		  }
		  
		  em.close();
		  
		  return false;
	}

}
