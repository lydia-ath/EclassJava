package com.example.app.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import com.example.app.dao.CourseDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.SecretariatDAO;
//import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;

public class SecretariatTest {
	
	Secretariat secretariat;
	
	@BeforeEach
	public void setUp() {
		secretariat = new Secretariat("Maria", "Tzanakaki","martz", "1234");
	}
	
	@Test
	void addSecretariat() {
		assertNotEquals(null, secretariat);
	}
	
	@Test 
	  void verifyUserName() { 
		  String username=secretariat.getUsername();
	      assertTrue("martz".equals(username)); 
	      }
	
	@Test 
	  void verifyUserPassword() { 
		  String password=secretariat.getPassword();
	      assertTrue("1234".equals(password)); 
	      }
}
	
