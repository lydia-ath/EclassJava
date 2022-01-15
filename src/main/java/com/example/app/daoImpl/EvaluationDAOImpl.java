package com.example.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.JPAUtil;
import com.example.app.domain.Course;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Professor;

public class EvaluationDAOImpl implements EvaluationDAO{

	private EntityManager em;
	
	public EvaluationDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}

	@Override
	public void save(Evaluation evaluation) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (evaluation.getEvaluationId() !=null) {
			em.merge(evaluation);
		} else {
			em.persist(evaluation);
		}
		
		tx.commit();
		
	}

	@Override
	public void delete(Evaluation evaluation){
        em.getTransaction().begin();
        em.remove(evaluation);
        em.getTransaction().commit();
    }

	@Override
	public List<Evaluation> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select e from Evaluation e", Evaluation.class );
		List<Evaluation> evaluations = q.getResultList();
		
		tx.commit();
		
		return evaluations;
		
	}
}
