package com.example.app.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**Embedable attribute*/
@Embeddable
public class Address {

	@Column(name="street")
	private String street;
	
	@Column(name="number")
	private int number;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@Column(name="zipcodes")
	private String zipcode;

	
	/** default constructor*/
	public Address() {
		
	}
	
	/** Constructor*/
	public Address(String street, int number, String city, String country, String zipcode) {
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
	}
	
	/** getters*/
	public String getStreet() {
		return street;
	}
	
	public int getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	/** setters*/
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
}
