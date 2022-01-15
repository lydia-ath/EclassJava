package com.example.app.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
    public void checkAdress() {
		Person person = new Person();
		Address address = new Address();
		address.setCity("Μαρούσι");
		address.setCountry("Greece");
		address.setStreet("Ηροδότου");
		address.setNumber(17);
		address.setZipcode("19015");
        person.setAddress(address);
        Assertions.assertEquals("Μαρούσι", person.getAddress().getCity());      
        Assertions.assertEquals("Greece", person.getAddress().getCountry());  
        Assertions.assertEquals("Ηροδότου", person.getAddress().getStreet() ); 
        Assertions.assertEquals(17, person.getAddress().getNumber()); 
        Assertions.assertNotEquals("15017", person.getAddress().getZipcode()); 
    }
	
	
	
	
	@Test
	public void findbyFirstname() {
		Person person = new Person();
		person.setFirstname("lydia");
		Assertions.assertEquals("lydia", person.getFirstname()); 
	}
	
	@Test
	public void findbyLastname() {
		Person person = new Person();
		person.setLastname("lydia");
		Assertions.assertEquals("lydia", person.getLastname()); 
	}

}

