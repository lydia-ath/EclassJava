package com.example.app.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.Normalizer.Form;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.SecretariatDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;
import com.example.app.domain.Course;
import com.example.app.domain.Person;
import com.example.app.domain.Professor;
import com.example.app.domain.Secretariat;
import com.example.app.domain.User;

public class SecetariatResourceTest extends JerseyTest {
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(SecretariaResource.class, DebugExceptionMapper.class);
		
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
	public void testGetAllSecretariats() {
		
		Invocation.Builder builder = target("/secretariats/all").request();
		
		List<Secretariat> secretariats = builder.get(new GenericType<List<Secretariat>> () {}); 
		assertEquals(3, secretariats .size());
	}
	
	
	@Test
	public void testByLastNameAsQueryParam() { 
		
		String name;
		String  name1 = "ΤΖΑΝΑΚΑΚΗ";
		Invocation.Builder builder1 = target("/secretariats/lastname").queryParam("secretariatName", name1).request();
		List<Secretariat> professors1 = builder1.get(new GenericType<List<Secretariat>> () {}); 
		name = professors1.get(0).getLastname();
		
		assertEquals(name, name1);
		
	}
	
	/**alone runs --> when we run all junit tests together --> transaction already active */
	/*@Test
	public void testCreateNewSecretariat() {
		
		SecretariatInfo secretariatinfo = new SecretariatInfo("Kiriaki", "Tzanakaki", "kiriaki", "1234");
		Response response = target("/secretariats/save").request().post(Entity.entity(secretariatinfo, MediaType.APPLICATION_JSON));
		
		SecretariatDAO secretariatdao = new SecretariatDAOImpl(); 
		
		List<Secretariat> secretariats = secretariatdao.findAll();
		Assert.assertEquals(3,secretariats.size());
	}*/
	

	@Test
	public void testTimeTable() { 
		
		Invocation.Builder builder1 = target("/secretariats/createTimeTable")
									  .queryParam("schoolClass", "Α")	
									  .queryParam("studentclass", "A1")
				                      .queryParam("coursename", "Μαθηματικά")
				                      .queryParam("day", "Δευτέρα")
				                      .queryParam("hour", "ΠΡΩΤΗ ΩΡΑ")
				                      .queryParam("professorName", "DROUGKA")
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		
		assertTrue(result);
		
	}
	
	@Test
	public void testAddCourseToDB() {
		
		Invocation.Builder builder1 = target("/secretariats/addLesson")
				  .queryParam("courseName", "Χημεία")
				  .queryParam("courseHours", 5)
				  .request();
		
		List<Course> courses = builder1.get(new GenericType<List<Course>> () {}); 
		assertEquals(4, courses.size());
		
	}
	
	@Test
	public void testStudentsToClass() { 
		
		Invocation.Builder builder1 = target("/secretariats/students")
									  .queryParam("schoolClass", "A")	
									  .queryParam("studentclassCode", 1)
				                      .queryParam("studentCode", 1)
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		
		assertTrue(result);
	}
	
	@Test
	public void testTimeTable1() { 
		
		Invocation.Builder builder1 = target("/secretariats/createTimeTable")
									  .queryParam("schoolClass", "B")	
									  .queryParam("studentclass", "B1")
				                      .queryParam("coursename", "Μαθηματικά")
				                      .queryParam("day", "Δευτέρα")
				                      .queryParam("hour", "ΠΡΩΤΗ ΩΡΑ")
				                      .queryParam("professorName", "DROUGKA")
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		
		assertTrue(result);
		
	}
	
	@Test
	public void testEditUser() { 
		
		Invocation.Builder builder1 = target("/secretariats/editUser")
									  .queryParam("lastName", "DROUGKA")	
									  .queryParam("newUserName", "tzanak")
				                      .queryParam("newPassword", "1234567890")
				                      .request();
		
		String password = builder1.get(new GenericType<String> () {});
		assertEquals(password,"1234567890" );
		
	}
	
	@Test
	public void testStudentReport() { 
		
		Invocation.Builder builder1 = target("/secretariats/report")
									  .queryParam("schoolClassCode", 1)	
									  .queryParam("studentclassCode", 1)
				                      .queryParam("studentCode", 1)
				                      .request();
		
		boolean result = builder1.get(new GenericType<Boolean> () {});
		assertTrue(result);
		
	}
	
}
