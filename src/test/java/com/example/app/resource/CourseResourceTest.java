
 package com.example.app.resource;
  
 import static org.junit.Assert.assertEquals;
  
  import java.util.List;
  
  import org.glassfish.jersey.server.ResourceConfig; 
  import org.glassfish.jersey.test.JerseyTest;
  import org.glassfish.jersey.test.TestProperties;
  import org.junit.Test; import org.junit.jupiter.api.AfterEach;
  import org.junit.jupiter.api.BeforeEach;
  
  import com.example.app.dao.CourseDAO; 
  import com.example.app.dao.Initializer;
  import com.example.app.dao.JPAUtil; 
  import com.example.app.daoImpl.CourseDaoImpl; 
  import com.example.app.domain.Course;
  
  import javax.persistence.EntityManager; 
  import javax.ws.rs.client.ClientBuilder; 
  import javax.ws.rs.client.Invocation; 
  import javax.ws.rs.core.GenericType;
  
 public class CourseResourceTest extends JerseyTest{
  
	  CourseDAO coursedao;
	  
	  @Override protected javax.ws.rs.core.Application configure() {
	    forceSet(TestProperties.CONTAINER_PORT, "0");
	    return new
	  ResourceConfig(CourseResource.class, DebugExceptionMapper.class);
	  
	  }
	  
	  @BeforeEach public void setUp() throws Exception {
  
		  Initializer initializer = new Initializer(); initializer.prepareData(); 
		  //calls  constructor of superclass 
		  super.setUp(); coursedao = new
		  CourseDaoImpl(); 
	  }
	  
	  @AfterEach public void cleanUpEach(){
		  System.out.println("Data base will be intialized.."); EntityManager em =
		  JPAUtil.getCurrentEntityManager(); em.close();
	  }
  
  
	  @Test public void testGetAllCourses() { 
		  Invocation.Builder builder = target("courses/allCourses").request();
		  List<Course> courses = (List<Course>) builder.get((new GenericType<List<Course>>() {})); //List<Course> courses1 =
		  //target("courses").request().get(new GenericType<List<Course>>() {});
		  assertEquals(3, courses.size()); 
	}
	  
 }
  
 