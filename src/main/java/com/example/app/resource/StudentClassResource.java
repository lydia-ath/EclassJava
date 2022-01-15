package com.example.app.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.JPAUtil;
import com.example.app.dao.StudentClassDAO;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.domain.StudentClass;

@XmlRootElement
@Path("studentclasses")
public class StudentClassResource {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("allClasses/all")
	@Produces("application/xml")
	@XmlTransient
	public List<StudentClass> listAlltudentClasses(){
		StudentClassDAO studentclassdao;
		studentclassdao = new StudentClassDAOImpl();
		
		List<StudentClass> studentclasses = studentclassdao.findAll();
		
		return studentclasses ;
		
		}
	
	@POST
	@Path("/allStudentClasses")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudentClass(StudentClassInfo studentclassinfo) {
		
		EntityManager em = getEntityManager();

		StudentClass studentclass = studentclassinfo.getStudentClass(em);
		
		StudentClassDAO studentclassdao = new StudentClassDAOImpl();
		studentclassdao.save(studentclass);
		

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newStudentClassUri = ub.path(Long.toString(studentclass.getStudentClassId())).build();

		em.close();

		return Response.created(newStudentClassUri).build();
	}
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}

}
