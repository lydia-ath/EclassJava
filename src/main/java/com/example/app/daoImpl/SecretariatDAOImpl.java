package com.example.app.daoImpl;

import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.SecretariatDAO;
import com.example.app.domain.Course;
import com.example.app.domain.Professor;
import com.example.app.domain.Secretariat;

public class SecretariatDAOImpl implements SecretariatDAO{
	
	private EntityManager em;
	protected static List<Secretariat> secretariats = new ArrayList<Secretariat>();
	
	public SecretariatDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretariat> findAll() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select s from Secretariat s", Secretariat.class);
		List<Secretariat> secretariats =  q.getResultList();
		
		tx.commit();
		
		return secretariats;
	}

	@Override
	public void save1(Secretariat secretariat) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (secretariat.getPersonId() !=null) {
			em.merge(secretariat);
		} else {
			em.persist(secretariat);
		}
		
		tx.commit();
				
	}

	@Override
	public void delete1(Secretariat secretariat) {
		if (! secretariats.contains(secretariat)) {
			secretariats.remove(secretariat);    
        } 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretariat> findBySecretariatName1(String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery( "select s from Person s " +
				  " where s.lastname like :name"); 
		  q.setParameter("name", name);
		 
		List<Secretariat> secretariats = (List<Secretariat>)q.getResultList();
		
		tx.commit();
		return secretariats;
	}

	@Override
	public void save(Secretariat secretariat) {
		// TODO Auto-generated method stub
		
	}

	
}
