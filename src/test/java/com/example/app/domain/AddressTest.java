package com.example.app.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {

	@Test
    public void checkStreet() {
		Address address = new Address();
		address.setStreet("Ηροδότου");
        Assertions.assertEquals("Ηροδότου", address.getStreet() ); 
    }
	
	@Test
    public void checkNumber() {
		Address address = new Address();
		address.setNumber(17); 
        Assertions.assertEquals(17, address.getNumber() ); 
    }
	
	@Test
    public void checkCity() {
		Address address = new Address();
		address.setCity("Μαρούσι"); 
        Assertions.assertEquals("Μαρούσι", address.getCity() ); 
    }
	
	@Test
    public void checkContry() {
		Address address = new Address();
		address.setCountry("Greece"); 
        Assertions.assertNotEquals("Paris", address.getCountry() ); 
    }
	
	@Test
    public void checkZipcode() {
		Address address = new Address();
		address.setZipcode("19015"); 
        Assertions.assertNotEquals("15014", address.getZipcode() ); 
    }


}


