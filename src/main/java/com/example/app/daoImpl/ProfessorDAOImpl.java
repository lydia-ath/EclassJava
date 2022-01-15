package com.example.app.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.domain.Course;
import com.example.app.domain.Professor;
import com.example.app.domain.Teaching;

public class ProfessorDAOImpl implements ProfessorDAO{
	
	private EntityManager em;
	protected static List<Professor> professors = new ArrayList<Professor>();
	
	public ProfessorDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}


	@Override
	public void save(Professor professor) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (professor.getPersonId() !=null) {
			em.merge(professor);
		} else {
			em.persist(professor);
		}
		
		tx.commit();
		
	}

	
	@Override
	public void delete(Professor professor){
        em.getTransaction().begin();
        em.remove(professor);
        em.getTransaction().commit();
    }
	
	public void delete1(Professor professor) {
        if (!em.contains(professor)) {
            professor = em.merge(professor);
        }
        em.remove(professor);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> findAll() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select p from Professor p", Professor.class);
		List<Professor> professors = q.getResultList();
		tx.commit();
		return professors;
	}

	  @SuppressWarnings("unchecked")
	  @Override 
	  public boolean findByProfessorName(String name) {
	  
	  EntityTransaction tx = em.getTransaction(); tx.begin();
	  
	  Query q = em.createQuery( "select p from Professor p"); 
	 
	  List<Professor> professors = q.getResultList();
	  	  
	  tx.commit();
	  
	  for(Professor professor :professors) {
		  
		  if(professor.getFirstname().equals(name)) {
			  
			  return true;
		  }
	  }
	  
	  em.close();
	  
	  return false;
	  
	  }

	@Override
	public List<Professor> findByProfessorLastName(String name) {
	  EntityTransaction tx = em.getTransaction(); 
	  tx.begin();
  
	  Query q = em.createQuery( "select p from Person p " +
			  " where p.lastname like :name"); 
	  q.setParameter("name", name);
	  List<Professor> professors = q.getResultList(); 
  
	  tx.commit(); 
	  return professors;

}

	@Override
	public void save1(Professor professor) {
        if (! professors.contains(professor)) {
        	professors.add(professor);    
        }        
	
}


	@Override
	public List<Teaching> findProfessorTeachings(String name) {
	  EntityTransaction tx = em.getTransaction(); 
	  tx.begin();
  
	  Query q = em.createQuery( "select t from Teaching t " +
			  " where t.professor.firstname like :name"); 
	  q.setParameter("name", name);
	  List<Teaching> teachings = q.getResultList();
  
	  tx.commit(); 
	  return teachings;
	}


	public boolean deleteProfessors(long professorId) {
	
		EntityTransaction tx = em.getTransaction();
		tx.begin();

	try {
		Professor professor = em.getReference(Professor.class, professorId);
		em.remove(professor);
	} catch (EntityNotFoundException e) {
		tx.rollback();
		return false;
	}

		tx.commit();

	return true;

    }
}
