package com.example.app.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.dao.CourseDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.domain.Course;
import com.example.app.domain.Teaching;

@XmlRootElement
@Path("courses")
public class CourseResource {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/allCourses")
	//@Produces("application/xml")
	public List<Course> listAllCourses(){
		
		CourseDAO coursedao = new CourseDaoImpl();
		List<Course> courses = coursedao.findAll();
		
		return courses;
	}
	
	@GET
	@Path("courses/teachings/{courseTitle}")
	@Produces("application/xml")
	public List<Teaching> getCourseTeachings(@PathParam("courseTitle") 
				String courseTitle) {
		CourseDAO coursedao = new CourseDaoImpl();
		List<Teaching> teachings = coursedao.findCourseTeachings(courseTitle);
		
		return teachings;
	
	}

}
