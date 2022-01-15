package com.example.app.resource;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.dao.StudentClassDAO;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.domain.Person;
import com.example.app.domain.Secretariat;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;
import com.example.app.domain.User;

/**
 * Value object for transferring secretariat data over the wire ...
 *
 */

@XmlRootElement
public class SecretariatInfo extends User{
	
	/** Default Constructor*/
	public SecretariatInfo() {
		
	}
	
	/** Constructor*/
	public SecretariatInfo(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
	}
	
	/** getters and setters can be defined in superclasses*/
	
	public List<StudentClass> getStudentClasses(){
		
		StudentClassDAO studentclassdao = new StudentClassDAOImpl();
		List<StudentClass> studentclasses = studentclassdao.findAll();
		
		return studentclasses;
		
	}
	
	/**used for put rest services*/
	public Secretariat getSecretariat(EntityManager em) {

		Secretariat secretariat = null;

		if (getPersonId() != null) {
			secretariat = em.find(Secretariat.class, getPersonId());
		} else {
			secretariat = new Secretariat();
		}

		secretariat.setFirstname(getFirstname());
		secretariat.setLastname(getLastname());
		secretariat.setPassword(getPassword());
		secretariat.setUsername(getUsername());
		em.close();

		return secretariat;
	}
	

}
