package com.example.app.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.TeachingDAO;
import com.example.app.domain.Teaching;

public class TeachingClassDaoImplTest {
	
	private TeachingDAO teachingdao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		teachingdao = new TeachingDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<Teaching> teaching= teachingdao.findAll();
		assertEquals(5, teaching.size());
		
	}
	
}
