package com.example.app.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.Initializer;
import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.SecretariatDAO;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;
import com.example.app.domain.Professor;
import com.example.app.domain.Secretariat;

@XmlRootElement
@XmlTransient 
@Path("users")
public class UserResource {

	@Context
	UriInfo uriInfo;
	
	/*List that contains all usernames that are registered in the system*/
	static List <String> usernames = new ArrayList<>();

	/*List that contains all passwords that are registered in the system*/
	static List <String> passwords = new ArrayList<>();
	
	public static void initialize() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	}
	
	/** Method in order professors to have access in the system */
	@GET
	@Path("/loginProfessor")
	@Consumes( MediaType.APPLICATION_XML )
	public static boolean loginProfessor(@QueryParam("username") String username 
			,@QueryParam("password") String password) {
		
		boolean access = false;
		initialize();
		
		ProfessorDAO profdao =new ProfessorDAOImpl();
		
		/*find all usernames and passwords of the registered professors*/
		List <Professor> profs = profdao.findAll();
		
		/** find all usernames and passwords of the registered users*/
		for (Professor p : profs) {
			usernames.add(p.getUsername());
			passwords.add(p.getPassword());
		}
	
		if(usernames.contains(username) && passwords.contains(password)) {
			access = true;
		}
		
		return access;
	}
	
	/** Method in order secretariats to have access in the system */
	@GET
	@Path("/loginSecretaria")
	@Consumes( MediaType.APPLICATION_XML )
	public static boolean loginSecretaria(@QueryParam("username") String username 
			,@QueryParam("password") String password) {
		
		boolean access = false;
		initialize();
		
		SecretariatDAO secdao = new SecretariatDAOImpl();
		List <Secretariat> secs = secdao.findAll();
		
		/** find all usernames and passwords of the registered users*/
		for (Secretariat s : secs) {
			usernames.add(s.getUsername());
			passwords.add(s.getPassword());
		}
		
		if(usernames.contains(username) && passwords.contains(password)) {
			access = true;
		}
		
		return access;
	}
}

