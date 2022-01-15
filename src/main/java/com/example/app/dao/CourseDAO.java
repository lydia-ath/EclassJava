package com.example.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.app.domain.Course;
import com.example.app.domain.Teaching;

public interface CourseDAO {

	//Course find(int courseId);
	void save(Course course);
	List<Course> findAll();
	List<Course> findAll(EntityManager em);
	List<Course> findCourseName(String coursename);
	List<Teaching> findCourseTeachings(String course);
}
