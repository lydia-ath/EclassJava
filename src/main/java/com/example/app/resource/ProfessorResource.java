package com.example.app.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.dao.StudentClassDAO;
import com.example.app.dao.StudentDAO;
import com.example.app.dao.TeachingDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.ScheduleSlotDAOImpl;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.daoImpl.TeachingDAOImpl;
import com.example.app.domain.Classes;
import com.example.app.domain.Comments;
import com.example.app.domain.Course;
import com.example.app.domain.Days;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Hours;
import com.example.app.domain.Professor;
import com.example.app.domain.ScheduleSlot;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;

import javax.ws.rs.core.Response.Status;

@XmlRootElement
@XmlTransient 
@Path("/professors")
public class ProfessorResource {
	
	@Context
	UriInfo uriInfo;
	
	static HashMap<String, Integer> classALessons = new HashMap<String, Integer>();
	static HashMap<String, Integer> classBLessons = new HashMap<String, Integer>();
	static HashMap<String, Integer> classCLessons = new HashMap<String, Integer>();
	static List<ScheduleSlot> scheduleSlots = new ArrayList<>();
	static List<String> teachingDays = new ArrayList<>();
	static List<String> teachinghours = new ArrayList<>();
	static List<StudentClass> studentclasses = new ArrayList<>();
	static List<String> schoolclasses = new ArrayList<>();
	static CourseDAO coursedao = new CourseDaoImpl();
	static List<Course> courses = coursedao.findAll();
	
	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
	private List<Teaching> teachings = new ArrayList<>();

	
	public static void initialize() {
		
		Initializer initializer = new Initializer();
		initializer.prepareData();
	}

	public static List<StudentClass> getStudentClasses(){
		
		StudentClassDAO studentclassdao = new StudentClassDAOImpl();
		List<StudentClass> studentclasses = studentclassdao.findAll();
		
		return studentclasses;
		
	}

	public boolean checkLesson(HashMap<String, Integer> lessons, String lesson) {
		
		boolean result=false;
		
		List<String> lessons1 = new ArrayList<>();
	
		for(String lesson1 :lessons.keySet()) {
			lessons1.add(lesson1);
		}
		if(lessons1.contains(lesson)) {
			result=true;
		}
		
		return result;
	}

	public boolean checkTeachingDay(String day) {
		
		if(teachingDays.contains(day)) {
			
			return true;
		}
		
		return false;
		
	}

	public boolean checkTeachingHour(String hour) {
		
		if(teachinghours.contains(hour)) {
			return true;
		}
		return false;
	}

	public boolean checkSchoolClass(String sclass) {
		
		if(schoolclasses.contains(sclass)) {
			return true;
		}
		return false;
	}

	public boolean checkStudentClass(String tmima) {
		
		for(StudentClass sclass :studentclasses) {
			
			if(sclass.getClassName().equalsIgnoreCase(tmima)) {
				return true;
			}
		}
		return false;	
	}

	public List<Teaching> getTeachings() {
		return teachings;
	}
	
	@GET
	@Path("/allProfessors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> listAllProfessors(){
		
		ProfessorDAO professordao = new ProfessorDAOImpl();
		List<Professor> professors = professordao.findAll();
		
		return professors;
	}
	
	
	@GET
	@Path("{professorLastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorDetails(@PathParam("professorLastName") 
	String professorLastName) {
		
		ProfessorDAO professordao = new ProfessorDAOImpl();
		List<Professor> professors = professordao.findByProfessorLastName(professorLastName);
		
		return professors;
		
	}
	
	@GET
	@Path("/lastname")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorDetailsWithQueryParam(@QueryParam("professorLastName") 
	String professorLastName) {
		
		ProfessorDAO professordao = new ProfessorDAOImpl();
		List<Professor> professors = professordao.findByProfessorLastName(professorLastName);
		
		return professors;
		
	}
	
	@GET
	@Path("/lastname/teachings")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor addTeaching(@QueryParam("professorLastName") 
	String professorLastName, @QueryParam("teachingHours") 
	int teachingHours) {
		TeachingDAO teachingdao = new TeachingDAOImpl();
		ProfessorDAO professordao = new ProfessorDAOImpl();
		List<Professor> professors = professordao.findByProfessorLastName(professorLastName);
		Teaching teaching = new Teaching(teachingHours);
		Professor professor = professors.get(0);
		teaching.setProfessor(professor);
		teachingdao.save(teaching);
		professor.addTeaching(teaching);
		professordao.save(professor);
		List<Professor> professors1 = professordao.findByProfessorLastName(professorLastName);
		Professor professor1 = professors1.get(0);
		return professor1;
		
	}
	
	@GET
	@Path("/lastname/teachings/checkHours")
	//@Produces(MediaType.APPLICATION_JSON)
	public int  checkTeachingHours(@QueryParam("professorLastName") 
										String professorLastName) {
		
		int totalHours=0;
		String firstName=null;
		ProfessorDAO professordao = new ProfessorDAOImpl();
		
		//Test before adding new teaching
		List<Professor> professors = professordao.findByProfessorLastName(professorLastName);
		firstName = professors.get(0).getFirstname();
		List<Teaching> teachings = professordao.findProfessorTeachings(firstName);
		for(Teaching teaching:teachings) {
			totalHours = totalHours + teaching.getTeachingHours();
		}
		
		return totalHours;
		
	}
	
	/** Μέθοδος: Προσθήκη καθηγητών στο σύστημα */
	@POST
	@Path("addProfessor")
	@Consumes("application/x-www-form-urlencoded")
	public Response addProfessor(
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("username") String username,
			@FormParam("password") String password ) {
	
			ProfessorDAO professordao = new ProfessorDAOImpl();	
			Professor professor = new Professor();
			professor.setFirstname(firstname);
			professor.setFirstname(lastname);
			professor.setUsername(username);
			professor.setPassword(password);
			professordao.save(professor);
						
			return Response.created(null).build();
		
		
	} 
	
	/** Μέθοδος: Διαγραφή καθηγητών απο το σύστημα */
	@DELETE
	@Path("deleteProfessor")
	public Response deleteProfessore(@PathParam("professorId") long professorId) {

		EntityManager em = getEntityManager();
		
		ProfessorDAO professordao = new ProfessorDAOImpl();
		boolean result = professordao.deleteProfessors(professorId);
		
		if (!result) {
			em.close();
			return Response.status(Status.NOT_FOUND).build();
		}

		em.close();
		return Response.ok().build();

	}

	@DELETE
	@Path("deleteProfessorByhisLastName")
	public Response deleteProfessorUsingLastNam(@QueryParam("lastname") String lastname) {

		EntityManager em = getEntityManager();
		
		ProfessorDAO professordao = new ProfessorDAOImpl();
		List <Professor> profs = professordao.findAll();
		for (Professor p: profs) {
			if (profs.contains(lastname)) {
				professordao.delete1(p);
				professordao.delete(p);
				professordao.deleteProfessors(p.getPersonId());
				em.close();
				return Response.ok().build();
			}
		}
		em.close();
		return Response.ok().build();

	}
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}
	
	/** Evaluation Method --> Teacher evaluates the progress of all students*/
	@GET
	@Path("/createEvaluation")
	@Produces(MediaType.APPLICATION_JSON)
	public Evaluation evaluationProcess(@QueryParam("professorcode")  int professorcode,
								  @QueryParam("classcode")  int classcode,
								  @QueryParam("studentclassCode")  int studentclassCode,
								  @QueryParam("studentCode") int studentCode,
								  @QueryParam("courseCode") int courseCode,
								  @QueryParam("courseGrade") int courseGrade){
			
			initialize();
		    StudentClass studentclass;			    
		    List<StudentClass> studentclassesA = new ArrayList<>();
		    List<StudentClass> studentclassesB = new ArrayList<>();
		    List<StudentClass> studentclassesC = new ArrayList<>();
		    
		    ProfessorDAO professordao = new ProfessorDAOImpl();
			Professor professor = professordao.findAll().get(professorcode);
			StudentDAO  studentdao = new StudentDAOImpl();
			List<Student> students1 = studentdao.findAll();
			System.out.println("professor " +" "+professor.getFirstname() +" "+professor.getLastname());
			List<Teaching> teaches = professor.getTeachings();
			 System.out.println("Αριθμός Μαθημάτων " +teaches.size());
			
			 if(classcode==1) {
				   System.out.println("Έχετε επιλέξει την τάξη Α-Γυμνασίου");
			   }else if(classcode==2) {
				   System.out.println("Έχετε επιλέξει την τάξη B-Γυμνασίου");
			   }else if(classcode==3){
				   System.out.println("Έχετε επιλέξει την τάξη Γ-Γυμνασίου");
			   }
			
			for (Teaching teaching : teaches) {
				
				List<Student> students = teaching.getStudentclass().getStudents();
				studentclass = teaching.getStudentclass();
				
				if(studentclass.getClassName().startsWith("A")) {
					   studentclassesA.add(studentclass); 
					}else if(studentclass.getClassName().startsWith("B")) {
						studentclassesB.add(studentclass); 
					}else if(studentclass.getClassName().startsWith("Γ")) {
						studentclassesC.add(studentclass); 
					}
			} 
			
			if(classcode==1) {
				System.out.println("Αριθμός τμημάτων τάξης " +studentclassesA.size());
				for(StudentClass sclass:studentclassesA) {
					System.out.println(sclass.getClassName());
				}
			}else if(classcode==2) {
				System.out.println("Αριθμός τμημάτων τάξης " +studentclassesB.size());
				for(StudentClass sclass:studentclassesB) {
					System.out.println(sclass.getClassName());
				}
			}else if(classcode==3) {
				System.out.println("Αριθμός τμημάτων τάξης " +studentclassesC.size());
				for(StudentClass sclass:studentclassesC) {
					System.out.println(sclass.getClassName());
				}
			} 
			
			System.out.println(
					"ΔΙΑΛΕΞΑΤΕ ΤΟ ΤΜΗΜΑ " + teaches.get(studentclassCode - 1).getStudentclass().getClassName() + " ΓΥΜΝΑΣΙΟΥ");
			System.out.println("Αριθμός Μαθητών " + students1.size()); 
			int stdCount = 1;
			for (Student std : students1) {
				System.out.println(stdCount + "  " + std.getFirstname() + "  " + std.getLastname());
				stdCount++;
			}
			Student student = students1.get(studentCode - 1);
			System.out.println("ΔΙΑΛΕΞΑΤΕ ΤΟ ΜΑΘΗΤΗ " + student.getFirstname() + " " + student.getLastname());

			/** Bring courses */
			displayCourses(courses);

			/* Choose courses */
			System.out.println("============================================");
			int counter = 1;
			Evaluation evaluation;
			EvaluationDAO evaluationdao = new EvaluationDAOImpl();		
			//do {
				System.out.println("ΔΙΑΛΕΞΤΕ ΜΑΘΗΜΑ");
				counter++;
				Course selectedCourse = courses.get(courseCode - 1);
				System.out.println("ΕΧΕΤΕ ΕΠΙΛΕΞΕΙ ΤΟ ΜΑΘΗΜΑ " + selectedCourse.getCourseName());
				System.out.println("ΕΙΣΑΓΕΤΕ ΤΟΝ ΒΑΘΜΟ ΓΙΑ ΤΟ ΜΑΘΗΤΗ");
				System.out.println("ΕΧΕΤΕ ΕΙΣΑΓΕΙ ΤΟΝ ΒΑΘΜΟ " + courseGrade);
				
				/** Comments in order to evaluate student */
				if (courseGrade <= 10) {
					//System.out.println(" Evaluation");
					 evaluation = new Evaluation(courseGrade, Comments.Bad);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
					 student.addEvalution(evaluation);				 
				} else if (courseGrade <= 13) {
					 evaluation = new Evaluation(courseGrade, Comments.Try_more);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
					 student.addEvalution(evaluation);
				} else if (courseGrade <= 15) {
					 evaluation = new Evaluation(courseGrade, Comments.Fair_enough);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
					 student.addEvalution(evaluation);
				} else if (courseGrade <= 17) {
					 evaluation = new Evaluation(courseGrade, Comments.Continue_like_this);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
				} else if (courseGrade <= 18) {
					 evaluation = new Evaluation(courseGrade, Comments.Good);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
					 student.addEvalution(evaluation);
				} else {
					 evaluation = new Evaluation(courseGrade, Comments.Excelent);
					 evaluation.setCourse(selectedCourse);
					 evaluation.setStudent(student);
					 student.addEvalution(evaluation);
				}
				 courses.remove(selectedCourse);
							
				displayCourses(courses);
			//} while (counter <= courses.size());
			return evaluation;

		}
	
	public void displayCourses(List<Course> courses) {
		int courseCounter = 1;
		for (Course cs : courses) {
			System.out.println(courseCounter + " ΟΝΟΜΑ ΜΑΘΗΜΑΤΟΣ: " + cs.getCourseName() + " & ΩΡΕΣ ΜΑΘΗΜΑΤΟΣ: "
					+ cs.getCourseHours());
			courseCounter += 1;
		}
	}

	
}
