package com.example.app.dao;

import java.util.List;
import com.example.app.domain.Secretariat;

public interface SecretariatDAO {

	//Secretariat find(long secretariatId);
	List<Secretariat> findAll();
	void save1(Secretariat secretariat);
	void delete1(Secretariat secretariat);
	List<Secretariat> findBySecretariatName1(String name);
	void save(Secretariat secretariat);
}
