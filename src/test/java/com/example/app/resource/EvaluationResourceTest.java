package com.example.app.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.domain.Comments;
import com.example.app.domain.Evaluation;
import javax.ws.rs.core.Response;

public class EvaluationResourceTest extends JerseyTest {
	
	@Override
	protected javax.ws.rs.core.Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(EvaluationResource.class, DebugExceptionMapper.class);
		
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
	public void testGetAllEvaluations() {
		//WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/jerseysample/rest/").path("hello");
		//Invocation.Builder builder = target("courses").request();
		Invocation.Builder builder = target("evaluations/all").request();
		List<Evaluation> evaluations = builder.get(new GenericType<List<Evaluation>> () {}); 
		assertEquals(3, evaluations.size());
	}
	
	/**Successful addition of a complete evaluation of student by a professor*/
	@Test
	public void testCreateNewEvaluation() {
		
		EvaluationInfo evaluationinfo = new EvaluationInfo(16, Comments.Fair_enough);
		
		Response response = target("/evaluations").request().post(Entity.entity(evaluationinfo, MediaType.APPLICATION_JSON));
		
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();
		
		List<Evaluation> evaluations = evaluationdao.findAll();
		Assert.assertEquals(4,evaluations.size());
	}
	
	/**Successful deletion of an evaluation of student */
	@Test
	public void testDeleteEvaluation() {
		
		Response response = target("/evaluations").request().delete();
		
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();
		
		List<Evaluation> evaluations = evaluationdao.findAll();
		Assert.assertEquals(3,evaluations.size());
	}


}
