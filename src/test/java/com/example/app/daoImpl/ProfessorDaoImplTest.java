 package com.example.app.daoImpl;
  
  import static org.junit.jupiter.api.Assertions.*;
  
  import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
  
  import com.example.app.dao.Initializer; import
  com.example.app.dao.ProfessorDAO; import
  com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.domain.Classes;
import com.example.app.domain.Course;
import
  com.example.app.domain.Professor;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;
  
  public class ProfessorDaoImplTest {
  
  private ProfessorDAO professordao;
  
  @BeforeEach public void setup() {
  
  Initializer initializer = new Initializer(); 
  initializer.prepareData();
  
  professordao = new ProfessorDAOImpl(); }
  

  
  @Test void findAll1() {
  
	  List<Professor> professors = professordao.findAll(); 
	  assertNotEquals(4, professors.size()); 

  }
  
  @Test void findByFirstName() {
  
	  boolean result = professordao.findByProfessorName("JOHN");
	  assertFalse(result); 

  }
  
  
  @Test void findByFirstName1() {
  
	  boolean result = professordao.findByProfessorName("mina");
	  assertFalse(result); 
  
  }
  
  @Test
  void delete() {
      Professor professor = new Professor();
      professor.setFirstname("Nikos"); 
      professor.setLastname("Kratiras");
      professor.setHoursOfEmployement(17);  
      professor.setUsername("Nick");  
      professor.setPassword("1234");
      professordao.delete(professor);
      List<Professor> professors = professordao.findAll();

      assertFalse(professors.contains(professor));	
      
  }
  
  @Test
  void checkTeachingHours() {
	  
	  List<Professor> professors = professordao.findByProfessorLastName("DROUGKA");
	  int teachingHours = 25;
	  int count = professors.get(0).getTeachings().get(0).getScheduleSlots().size();
	  //assertNotEquals(teachingHours, count);
	  assertTrue(count < teachingHours);
	  
  }
  
  @Test
  void saveProfessorToDB() {
	  
	  Professor professor = new Professor();
      professor.setFirstname("Nikos"); 
      professor.setLastname("Kratiras");
      professor.setHoursOfEmployement(17);  
      professor.setUsername("Nick");  
      professor.setPassword("1234");
      
      professordao.save(professor);
      assertTrue(professordao.findAll().size()==2);
	  
  }
  
  @Test
  void checkStudentClass() {
	  
	  Professor professor = new Professor();
      professor.setFirstname("Kostas"); 
      professor.setLastname("Dielas");
      professor.setHoursOfEmployement(17);  
      professor.setUsername("kost");  
      professor.setPassword("1234");
      Course course = new Course("INFORMATICS", "4");
      StudentClass sc1 = new StudentClass();
	  sc1.setClasses(Classes.A_class);
	  Teaching t1 = new Teaching();
	  t1.setStudentclass(sc1);
	  professor.addTeaching(t1);
	  professordao.save(professor);
	  List<Professor> professors = professordao.findByProfessorLastName("Dielas");
	  Assertions.assertEquals(Classes.A_class, professordao.findAll().get(1).getTeachings().get(0).getStudentclass().getClasses());
	  
  }
  
  /*test domain logic --> return all teachings of professor SOFIA*/
  /**SUCCESS*/
  @Test
  void findProfessorTeachings() {
	  assertEquals(professordao.findProfessorTeachings("SOFIA").size(), 5);
  }
  
  }
 