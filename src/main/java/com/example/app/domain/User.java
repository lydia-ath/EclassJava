package com.example.app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.SecretariatDAO;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;

@XmlRootElement
@Entity
public class User extends Person{
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	/** default constructor*/
	public User() {
		super();
	}

	/** constructor*/
	public User(String firstname, String lastname, String username, String password) {
		super(firstname, lastname);
		this.username = username;
		this.password = password;
	}

	@XmlTransient 
	/** getters*/
	public String getUsername() {
		return username;
	}

	@XmlTransient 
	public String getPassword() {
		return password;
	}

	/** setters*/
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean findusername(List<User> users, Object object) {
		boolean exist;
		if(users.contains(object)) {
			exist = true;
		}else {
			exist = false;
		}
		return exist;
	}
	
	public static boolean findpassword(List<User> userspasswords, Object object) {
		boolean exist;
		if(userspasswords.contains(object)) {
			exist = true;
		}else {
			exist = false;
		}
		return exist;
	}
	
}
