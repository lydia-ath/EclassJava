package com.example.app.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.TeachingDAO;
import com.example.app.domain.Teaching;

public class TeachingResourseTest extends JerseyTest{
	
	TeachingDAO teachingdao;
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(TeachingResourse.class, DebugExceptionMapper.class);
		
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
		/*calls constructor of superclass setUp*/
		super.setUp();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	public void testGetAllTeachings() {
		Invocation.Builder builder = target("teachings/allTeachings").request();
		List<Teaching> teachings = builder.get(new GenericType<List<Teaching>> () {}); 
		assertEquals(5, teachings.size());
	}

}
