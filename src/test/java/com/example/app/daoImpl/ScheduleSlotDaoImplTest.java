package com.example.app.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.domain.Days;
import com.example.app.domain.Hours;
import com.example.app.domain.ScheduleSlot;

public class ScheduleSlotDaoImplTest {
	
	private ScheduleSlotDAO scheduleslotdao;
	
	@BeforeEach
	public void setup() {
		
		/** Initializing*/
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		scheduleslotdao = new ScheduleSlotDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<ScheduleSlot> scheduleslots = scheduleslotdao.findAll();
		assertEquals(1, scheduleslots.size());
		
	}
	
	@Test
	void checkDays() {
		List<ScheduleSlot> scheduleslots = scheduleslotdao.findAll();
		assertEquals(Days.Friday, scheduleslots.get(0).getDays());
	}
	
	@Test
	void checkHours() {
		List<ScheduleSlot> scheduleslots = scheduleslotdao.findAll();
		assertEquals(Hours.forth_Hour, scheduleslots.get(0).getHours());
	}

}
