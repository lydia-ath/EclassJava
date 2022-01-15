package com.example.app.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentClassDAO;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;

class StudentClassResourceTest extends JerseyTest{

	@Inject
	StudentClassDAO studentclassdao;
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		return new ResourceConfig(StudentClassResource.class, DebugExceptionMapper.class);
		
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
	
	
	/**Successful addition of student class with post rest method*/
	@Test
	public void testCreateNewStudentClass() {
		
		StudentClassInfo studentclassinfo = new StudentClassInfo("Γ4");
		Response response = target("studentclasses/allStudentClasses").request().post(Entity.entity(studentclassinfo, MediaType.APPLICATION_JSON));
		
		StudentClassDAO studentclassdao = new StudentClassDAOImpl();
		
		List<StudentClass> studentclasses = studentclassdao.findAll();
		Assert.assertEquals(26,studentclasses.size());
	}
	
	/**alone runs , when we run all junit tests together --> error --> address already in use: bind*/
	/*@Test
	public void testGetAllStudentClasses() {
		
		Invocation.Builder builder = target("/studentclasses/allClasses/all").request();
		
		List<StudentClass> studentclasses = builder.get(new GenericType<List<StudentClass>> () {}); 
		assertEquals(5, studentclasses.size());
	}*/

}
