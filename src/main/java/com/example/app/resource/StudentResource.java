package com.example.app.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentDAO;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.domain.Student;
import com.example.app.school.SchoolException;
import com.example.app.util.HttpError;

@XmlRootElement
@XmlTransient 
@Path("students")
public class StudentResource {

	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("allStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> listAllStudents(){
		
		StudentDAO studentdao = new StudentDAOImpl();
		List<Student> students = studentdao.findAll();
		
		return students;
	}
	
	@GET
	@Path("stdlastname")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean getStudenrDetailsWithQueryParam(@QueryParam("studentLastName") 
	String studentLastName) {
		
		StudentDAO studentdao = new StudentDAOImpl();
		Boolean studentexist = studentdao.findByStudentLastName(studentLastName);
		
		return studentexist;
	}
	
	@GET
	@Path("/studentLastName")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean getStudentDetails(@PathParam("studentLastName") 
	String studentLastName) {
		
		StudentDAO studentdao = new StudentDAOImpl();
		Boolean studentexist = studentdao.findByStudentLastName(studentLastName);
		
		return studentexist;
		
	}
	
	@GET
	@Path("/studentLastName")
	public boolean getStudent(@QueryParam("studentLastName") String studentLastName) {
	
		StudentDAO studentdao = new StudentDAOImpl();
		Boolean studentexist = studentdao.findByStudentLastName(studentLastName);
	
		return studentexist;
	
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudent(StudentInfo studentinfo) {

		EntityManager em = getEntityManager();

		Student student = studentinfo.getStudent(em);
		// TODO: should validate student

		StudentDAO studentdao = new StudentDAOImpl();
		studentdao.save(student);
		

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newStudentUri = ub.path(Long.toString(student.getPersonId())).build();

		em.close();

		return Response.created(newStudentUri).build();
	}
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}
	
	/**Delete student with java rest*/
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("students/{studentId}")
	public Response deleteStudent(StudentInfo studentinfo){
		
		EntityManager em = getEntityManager();
		
		Student student = studentinfo.getStudent(em);
		
		StudentDAO studentdao = new StudentDAOImpl();
		Response response= null;
		try {
			studentdao.delete(student);
			response = Response.ok().build();
		} catch (SchoolException e) {
			
			HttpError error = HttpError.httpNotFoundError(e.getMessage());
			response = Response.status(Status.NOT_FOUND).entity(Entity.entity(error, MediaType.APPLICATION_JSON))
					.build();
		}
		
		em.close();
		return response;
		
	}

}
