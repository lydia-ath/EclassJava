package com.example.app.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.app.dao.Initializer;

class UserTest {
	
	@Test
    public void checkAdress() {
		User user = new User();
		Address address = new Address();
		address.setCity("Μαρούσι");
		address.setCountry("Greece");
		address.setStreet("Ηροδότου");
		address.setNumber(17);
		address.setZipcode("19015");
        user.setAddress(address);
        Assertions.assertEquals("Μαρούσι", user.getAddress().getCity());      
        Assertions.assertEquals("Greece", user.getAddress().getCountry());  
        Assertions.assertEquals("Ηροδότου", user.getAddress().getStreet() ); 
        Assertions.assertEquals(17, user.getAddress().getNumber()); 
        Assertions.assertNotEquals("15017", user.getAddress().getZipcode()); 
    }
	
	
	@Test
	public void checkUsername() {
		User user1 = new User();
		user1.setUsername("lydia");
		Assertions.assertEquals("lydia", user1.getUsername()); 
	}
	
	@Test
	public void checkPassword() {
		User user2 = new User();
		user2.setPassword("1234");
		Assertions.assertEquals("1234", user2.getPassword()); 
	}
	
	@Test
	public void findExistenceOfUsername() {
		List <User> usersusernames = new ArrayList<>();
		User user = new User();
		user.setUsername("soso");
		usersusernames.add(user);
		boolean result = user.findusername(usersusernames, "sisi");
		Assertions.assertFalse(result); 
	}
	
	@Test
	public void findExistenceOfPassword() {
		List <User> userspasswords = new ArrayList<>();
		User user = new User();
		user.setPassword("kiki");
		userspasswords.add(user);
		boolean result = User.findpassword(userspasswords, "kiki");
		Assertions.assertFalse(result); 
	}
	
	@Test
	public void updateUsername() {
		User user = new User();
		user.setUsername("sofia");
		user.setUsername("sofia1998");
		assertEquals("sofia1998",user.getUsername());
	}

	@Test
	public void updatePassword() {
		User user = new User();
		user.setPassword("1234");
		user.setPassword("1234567");
		assertEquals("1234567",user.getPassword());
	}


}
