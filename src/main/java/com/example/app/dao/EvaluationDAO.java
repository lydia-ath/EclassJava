package com.example.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.app.domain.Course;
import com.example.app.domain.Evaluation;

public interface EvaluationDAO {

	//Evaluation find(int evaluationId);
	void save(Evaluation evaluation);
	void delete(Evaluation evaluation);
	List<Evaluation> findAll();
	
}
