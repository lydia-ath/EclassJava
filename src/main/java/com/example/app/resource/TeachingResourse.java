package com.example.app.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.TeachingDAO;
import com.example.app.daoImpl.TeachingDAOImpl;
import com.example.app.domain.Teaching;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Path("teachings")
public class TeachingResourse {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/allTeachings")
	@XmlTransient
	public List<Teaching> listAllTeachings(){
		TeachingDAO teachingdao;
		teachingdao = new TeachingDAOImpl();
		
		List<Teaching> teachings = teachingdao.findAll();
		
		return teachings ;
		
		}
}
	
