package com.example.app.resource;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.domain.Professor;

class UserResourceTest extends JerseyTest{

	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(UserResource.class, DebugExceptionMapper.class);
		
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
		/** calls constructor of superclass setUp*/
		super.setUp();
		
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	/**Permission permitted for professor that is registrered in the system*/
	@Test
	public void testProfessorLogin() { 
		
		Invocation.Builder builder1 = target("/users/loginProfessor")
									  .queryParam("username", "sofie")	
									  .queryParam("password", "1234")
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		assertTrue(result);	
	}
	
	/**Permission denied for secretariat that not registrered in the system*/
	@Test
	public void testSecretariatLogin() { 
		
		Invocation.Builder builder1 = target("/users/loginSecretaria")
									  .queryParam("username", "kiriakos")	
									  .queryParam("password", "1234")
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		assertFalse(result);	
	}

}
