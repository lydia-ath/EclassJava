package com.example.app.daoImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.SecretariatDAO;
import com.example.app.domain.Secretariat;

public class SecretariatClassDaoImpl {
	
	private SecretariatDAO secretariatdao;
	
	@BeforeEach
	public void setup() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	
		secretariatdao = new SecretariatDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	void findAll() {
		
		List<Secretariat> secretariats = secretariatdao.findAll();
		assertEquals(3, secretariats.size());
	}

	
	@Test
	void findAll1() {
		
		List<Secretariat> secretariats = secretariatdao.findAll();
		assertEquals(3, secretariats.size());
	}
	
	
	@Test
	void  findByFirstName() {
		
		List<Secretariat> secretariats = secretariatdao.findBySecretariatName1("Maria");
		assertEquals(0, secretariats.size());
	}
	
	@Test
	void  findByLastName1() {
		
		List<Secretariat> secretariats = secretariatdao.findBySecretariatName1("ΤΖΑΝΑΚΑΚΗ");
		String name = secretariats.get(0).getLastname();
		assertTrue("ΤΖΑΝΑΚΑΚΗ".equals(name));
	}
	

}
