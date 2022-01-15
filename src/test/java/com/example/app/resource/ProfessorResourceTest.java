package com.example.app.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlTransient;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Professor;
import com.example.app.domain.Teaching;

import junit.framework.Assert;

@XmlTransient 
public class ProfessorResourceTest extends JerseyTest{
	
	ProfessorDAO professordao; 
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(ProfessorResource.class, DebugExceptionMapper.class);
		
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
		/*calls constructor of superclass setUp*/
		super.setUp();
		professordao = new ProfessorDAOImpl();
	}
	
	@AfterEach
	public void cleanUpEach(){
		System.out.println("Data base will be intialized..");
		EntityManager em = JPAUtil.getCurrentEntityManager();
		em.close();
	}
	
	@Test
	public void testGetAllProfessors() {
		Invocation.Builder builder = target("/professors/allProfessors").request();
		
		List<Professor> professors = builder.get(new GenericType<List<Professor>> () {}); 
		assertEquals(1, professors.size());
	}
	
	@Test
	public void testByLastName() { 
		
		String name;
		Invocation.Builder builder = target("/professors/allProfessors").request();
		List<Professor> professors = builder.get(new GenericType<List<Professor>> () {}); 
		
		name = professors.get(0).getLastname();
		
		assertEquals(name, "DROUGKA");
		
		}
	
	@Test
	public void testByLastNameAsPathParam() { 
		
		String name;
		Invocation.Builder builder = target().path("professors/DROUGKA").request();
		List<Professor> professors = builder.get(new GenericType<List<Professor>> () {}); 
		//Invocation.Builder builder = target("professors/lastname/{professorLastName}").resolveTemplate("brand", "BMW")
		
		name = professors.get(0).getLastname();
		
		assertEquals(name, "DROUGKA");
		
		}
	
	@Test
	public void testByLastNameAsPathParam1() { 
		
		Invocation.Builder builder = target().path("professors/KARTESIOS").request();
		List<Professor> professors = builder.get(new GenericType<List<Professor>> () {}); 
		assertTrue(professors.isEmpty());
		
		}
	
	@Test
	public void testByLastNameAsQueryParam() { 
		
		String name;
		String  name1 = "DROUGKA";
		Invocation.Builder builder1 = target("/professors/lastname").queryParam("professorLastName", name1).request();
		List<Professor> professors1 = builder1.get(new GenericType<List<Professor>> () {}); 
		name = professors1.get(0).getLastname();
		
		assertEquals(name, name1);
		
		}
	
	@Test
	public void testProfessorTeahings() { 
		
		String  name1 = "DROUGKA";
		Invocation.Builder builder1 = target("/professors/lastname").queryParam("professorLastName", name1).request();
		List<Professor> professors1 = builder1.get(new GenericType<List<Professor>> () {}); 
		List<Teaching> teachings= professors1.get(0).getTeachings();
		
		assertEquals(0,teachings.size());
		
		}
	
	@Test
	public void testAddProfessorTeahings() { 
		
		String  name1 = "DROUGKA";
		String  firstName=null;
		ProfessorDAO professordao = new ProfessorDAOImpl();
		
		/** Test before adding new teaching*/
		List<Teaching> teachings1 = professordao.findProfessorTeachings("SOFIA");
		int sz1 = teachings1.size();
		assertEquals(5,sz1);
		
		Invocation.Builder builder1 = target("/professors/lastname/teachings").queryParam("professorLastName", name1)
				.queryParam("teachingHours", 2).request();
		
		Professor professor = builder1.get(new GenericType<Professor> () {}); 
		firstName = professor.getFirstname();
		
		/** test after adding new teaching*/
		List<Teaching> teachings = professordao.findProfessorTeachings(firstName);
		int sz = teachings.size();
		assertEquals(6,sz);
		//assertEquals(name1,professor.getLastname());
		
		}
	
	@Test
	public void testTeachingHours() {
		
		String  name1 = "DROUGKA";
		Invocation.Builder builder1 = target("/professors/lastname/teachings/checkHours").queryParam("professorLastName", name1)
										.queryParam("teachingHours", 2).request();
		
		int hours = builder1.get(new GenericType<Integer> () {}); 
		assertTrue(hours<25);
	}
	
	/*@Test
	public void addNewProfessor() {
		
		Form form = new Form();
		form.param("firstname","JOHN");
		form.param("lastname", "KYRIAKOY");
		form.param("username", "jkyr");
		form.param("password", "1234");
		
		Client client = ClientBuilder.newClient();
		WebTarget professorTarget = client.target("http://localhost:9998/professors/");
		WebTarget target = professorTarget.path("addProfessor");
		Response response = target.request(MediaType.TEXT_PLAIN)
								  .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED ));
		//ProfessorDAO professordao = new ProfessorDAOImpl();
		
		assertEquals(201, response.getStatus());
		assertEquals(2,professordao.findAll().size());
	}*/
	
	/*@SuppressWarnings("deprecation")
	@Test
	public void testDeleteExistingProfessor() {
		// Find a professor and update his name
		ProfessorDAO profdao = new ProfessorDAOImpl();
		List<Professor> profs = profdao.findByProfessorLastName("DROUGKA");
		
		assertEquals(1, profs.size());
		Long prof = profs.get(0).getPersonId();
		
		Form form = new Form();
		form.param("professorId",Long.toString( prof));
		Client client = ClientBuilder.newClient();
		WebTarget professorTarget = client.target("http://localhost:9998/professors");
		WebTarget target = professorTarget.path("/deleteProfessor"); 
		
		Response response = target.request(MediaType.TEXT_PLAIN)
				  .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED ));
		
		assertEquals(500, response.getStatus());
		List<Professor> newprofs = profdao.findByProfessorLastName("DROUGKA");
		assertEquals(1, newprofs.size());

	}*/
	
	
	@Test
	public void testDeleteProfessor() {
		
		Invocation.Builder builder1 = target("/professors/deleteProfessorByhisLastName")
				  .queryParam("lastname", "DROUGKA")
                  .request();
		
		Response result = builder1.get(new GenericType<Response> () {});
		assertEquals(500, result.getStatus());
		
		ProfessorDAO profdao = new ProfessorDAOImpl();
		List<Professor> newprofs = profdao.findByProfessorLastName("DROUGKA");
		assertEquals(1, newprofs.size());

	}
	
	@Test
	public void testCreateEvaluation() { 
		
		Invocation.Builder builder1 = target("/professors/createEvaluation")
									  .queryParam("professorcode",0)
									  .queryParam("classcode", 1)	
									  .queryParam("studentclassCode", 1)
				                      .queryParam("studentCode", 2)
				                      .queryParam("courseCode", 3)
				                      .queryParam("courseGrade", 18)
				                      .request();
		
		Evaluation result = builder1.get(new GenericType<Evaluation> () {});
		assertTrue(result!=null);
		assertEquals(18, result.getGrade());
			
	}
	   
	   
}

