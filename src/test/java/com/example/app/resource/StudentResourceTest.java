package com.example.app.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlTransient;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentDAO;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.domain.Student;

@XmlTransient 
class StudentResourceTest extends JerseyTest{
	
	StudentDAO studentdao;
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(StudentResource.class, DebugExceptionMapper.class);
		
	}
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
		/*calls constructor of superclass setUp*/
		super.setUp();
		studentdao = new StudentDAOImpl();
	}

	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	/**Return all students and check their number*/
	@Test
	public void testGetAllStudents() {
		Invocation.Builder builder = target("students/allStudents").request();
		
		List<Student> students = builder.get(new GenericType<List<Student>> () {}); 
		assertEquals(8, students.size());
	}
	
	/**Student with lastName Athanasiou exists*/
	@Test
	public void testByLastNameAsPathParam1() { 
		
		Invocation.Builder builder1 = target("/students/studentLastName")
				  .queryParam("studentLastName", "Athanasiou")
                  .request();

		boolean result = builder1.get(new GenericType<Boolean> () {});

		assertTrue(result);
		
	}
	
	/**Student with lastName Gioti doesn't exists*/
	@Test
	public void testByLastNameAsQueryParam() {
		Invocation.Builder builder1 = target("/students/studentLastName")
				  .queryParam("studentLastName", "Gioti")
				  .request();

		boolean result = builder1.get(new GenericType<Boolean> () {});

		assertFalse(result);
	}
	
}
