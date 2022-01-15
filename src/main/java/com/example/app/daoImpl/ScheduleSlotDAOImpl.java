package com.example.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.domain.ScheduleSlot;

public class ScheduleSlotDAOImpl implements ScheduleSlotDAO{

	private EntityManager em;
	
	/** Constructor*/
	public ScheduleSlotDAOImpl() {
		
		em = JPAUtil.getCurrentEntityManager();		
	}

	@Override
	public ScheduleSlot find(long scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ScheduleSlot scheduleSlot) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (scheduleSlot.getScheduleId()!=null) {
			em.merge(scheduleSlot);
		} else {
			em.persist(scheduleSlot);
		}
		
		tx.commit();
		
	}

	@Override
	public void delete(ScheduleSlot scheduleSlot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ScheduleSlot> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("select sl from ScheduleSlot sl", ScheduleSlot.class );
		List<ScheduleSlot> scheduleSlots = q.getResultList();
		
		tx.commit();
		
		return scheduleSlots;
		
	}

}

