package com.example.app.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.example.app.dao.CourseDAO;
import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.Initializer;
import com.example.app.dao.JPAUtil;
import com.example.app.dao.ProfessorDAO;
import com.example.app.dao.ScheduleSlotDAO;
import com.example.app.dao.SecretariatDAO;
import com.example.app.dao.StudentClassDAO;
import com.example.app.dao.StudentDAO;
import com.example.app.dao.TeachingDAO;
import com.example.app.daoImpl.CourseDaoImpl;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.daoImpl.ProfessorDAOImpl;
import com.example.app.daoImpl.ScheduleSlotDAOImpl;
import com.example.app.daoImpl.SecretariatDAOImpl;
import com.example.app.daoImpl.StudentClassDAOImpl;
import com.example.app.daoImpl.StudentDAOImpl;
import com.example.app.daoImpl.TeachingDAOImpl;
import com.example.app.domain.Course;
import com.example.app.domain.Days;
import com.example.app.domain.Evaluation;
import com.example.app.domain.Hours;
import com.example.app.domain.Professor;
import com.example.app.domain.ScheduleSlot;
import com.example.app.domain.Secretariat;
import com.example.app.domain.Student;
import com.example.app.domain.StudentClass;
import com.example.app.domain.Teaching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@XmlRootElement
@XmlTransient 
@Path("/secretariats")
public class SecretariaResource {
		
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
	
	/**Μέθοδος: Κατάρτιση ωρολογίου σχολικού προγράμματος */
	@GET
	@Path("/createTimeTable")
	//@Produces(MediaType.APPLICATION_JSON)
	public boolean createTimeTable(@QueryParam("coursename") String coursename 
						,@QueryParam("schoolClass") String schoolClass
						,@QueryParam("studentclass") String studentclass
	                    ,@QueryParam("day") String day
	                    ,@QueryParam("hour") String hour
						,@QueryParam("professorName") String professorName) 
	{
		
		initialize();
		boolean result1 = true;
		boolean result2 = true;
		int totalHours =0;
		HashMap<String, Integer> classLessons = new HashMap<String, Integer>();
		
		if(schoolClass.equalsIgnoreCase("A")) {
			classLessons = classALessons;
		}else if (schoolClass.equalsIgnoreCase("Β") ) {
			classLessons = classBLessons;
		}else if (schoolClass.equalsIgnoreCase("C")) {
			classLessons = classCLessons;
		}
		
		if(this.checkLesson(classLessons, coursename)&& this.checkTeachingDay(day)
				&& this.checkTeachingHour(hour) && this.checkSchoolClass(schoolClass)
				&& this.checkStudentClass(studentclass)) {
			
			int teachingHours;
			int setHours=0;
			teachingHours = classALessons.get(coursename);
			Teaching teaching = new Teaching();
			teaching.setTeachingHours(teachingHours);
			
			Course course = new Course();
			ProfessorDAO  professordao = new ProfessorDAOImpl();
			Professor professor = professordao.findByProfessorLastName(professorName).get(0);
			course.setCourseName(coursename);
			course.setCourseHours(Integer.toString(teachingHours ));
			CourseDAO coursedao = new CourseDaoImpl();
			coursedao.save(course);
			teaching.setCourse(course);
			
			Days d = null;
			Hours h =null;
			
			ScheduleSlot scheduleslot = new ScheduleSlot();
			if(day.equals("Δευτέρα")) {
				scheduleslot.setDays(Days.Monday);
				d=Days.Monday;
			}else if(day.equals("Τρίτη")) {
				scheduleslot.setDays(Days.Tuesday);
				d=Days.Tuesday;
			}else if(day.equals("Τετάρτη")) {
				scheduleslot.setDays(Days.Wednesday);
				d=Days.Wednesday;
			}else if(day.equals("Πέπμτη")) {
				d=Days.Thursday;
				scheduleslot.setDays(Days.Thursday);
			}else {
				scheduleslot.setDays(Days.Friday);
				d=Days.Friday;
			}	
			
			if(hour.equals("ΠΡΩΤΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.first_Hour);
				h=Hours.first_Hour;
			}else if(hour.equals("ΔΕΥΤΕΡΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.second_Hour);
				h=Hours.second_Hour;
			}else if(hour.equals("ΤΡΙΤΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.third_Hour);
				h=Hours.third_Hour;
			}else if(hour.equals("ΤΕΤΑΡΤΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.forth_Hour);
				h=Hours.forth_Hour;
			}else if(hour.equals("ΠΕΜΠΤΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.fifth_Hour);
				h=Hours.fifth_Hour;
			}else if(hour.equals("ΕΚΤΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.sixth_Hour);
				h=Hours.sixth_Hour;
			}else if(hour.equals("ΕΒΔΟΜΗ ΩΡΑ")) {
				scheduleslot.setHours(Hours.seventh_Hour);
				h=Hours.seventh_Hour;
			}
			
			ScheduleSlotDAO scheduleslotdao = new ScheduleSlotDAOImpl();
			List<ScheduleSlot> scheduleSlots = scheduleslotdao.findAll();
			
			/** check day and hour logic*/
			for(ScheduleSlot scheduleSlot :scheduleSlots) {
				 System.out.println("scheduleSlot " + d+" "+scheduleSlot.getDays()+" "+h+" "+scheduleSlot.getHours());
				 Days day1 = scheduleSlot.getDays();
				 Hours hour1 = scheduleSlot.getHours();
				   if(day1.equals(d) && hour1.equals(h) ) {
						result1 = false;
						
						 System.out.println("Υπάρχει άλλο μάθημα στη συγκεκριμένη θέση..");
						 System.out.println("Διαλέξτε άλλη μέρα ή ώρα..");
				   }else {
					   totalHours+=1; 
				   }
			}
			
			/** check teaching hours logic*/
			TeachingDAO teachingdao = new TeachingDAOImpl();
			List<Teaching> teachings = teachingdao.findAll();
			
			for(Teaching teaching1 :teachings) {
				
				if(teaching1.getCourse().getCourseName().equals(coursename)) {
	
					setHours = teaching1.getTeachingHours(); 
				}
			}
			
			/** οι ώρες διδασκαλίας δεν πρέπει να υπερβαίνουν τις ώρες που επιτρέπονται για το μάθημα*/
			if(teachingHours<setHours) {
				result2=false;
			}
			
			if( totalHours<=25) {
				result2=false;
			}
			
			if(result1&&result2) {
				teaching.setProfessor(professor);
				teaching.addScheduleSlot(scheduleslot);
				scheduleslotdao.save(scheduleslot);
				teachingdao.save(teaching);
			}
		}
			
			return result1&&result2;
	}
		
		@GET
		@Path("/all")
		//@Produces("application/xml")
		public List<Secretariat> listAllSecretariats(){
			
			SecretariatDAO secretariatdao = new SecretariatDAOImpl();
			List<Secretariat> secretariats = secretariatdao.findAll();
			
			return secretariats;
			 
		}
		
		@GET
		@Path("{secreariatName}")
		@Produces("application/xml")
		public List<Secretariat> nameWithPathParam(@PathParam("secretariatName") 
		String secretariatName){
			
			SecretariatDAO secretariatdao = new SecretariatDAOImpl();
			List<Secretariat> secretariats = secretariatdao.findBySecretariatName1(secretariatName);
			
			return secretariats;
			 
		}
		
		@GET
		@Path("/lastname")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Secretariat> getSecretariatWithQueryParam(@QueryParam("secretariatName") 
		String secretariatName) {
			
			SecretariatDAO secretariatdao = new SecretariatDAOImpl();
			List<Secretariat> secretariats = secretariatdao.findBySecretariatName1(secretariatName);
					
			return secretariats;
			
		}
		
		@POST
		@Path("/save")
		 public ResponseBuilder saveSecretariat(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, 
                 @QueryParam("username") String username,  @QueryParam("password") String password) {
				  
			Secretariat secretariat = new Secretariat(firstname,lastname, username, password );
			SecretariatDAO secretariatdao = new SecretariatDAOImpl();
			secretariatdao.save(secretariat);
		
		 return Response.ok();
		}
		
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createSecretariat(SecretariatInfo secretariatinfo) {
			
			EntityManager em = getEntityManager();

			Secretariat secretariat = secretariatinfo.getSecretariat(em);
			
			SecretariatDAO secretariatdao = new SecretariatDAOImpl();
			secretariatdao.save1(secretariat);
			

			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newSecretariatUri = ub.path(Long.toString(secretariat.getPersonId())).build();

			em.close();

			return Response.created(newSecretariatUri).build();
		}
		
		protected EntityManager getEntityManager() {

			return JPAUtil.getCurrentEntityManager();

		}
		
		/**Μέθοδος: Καταχώρηση Μαθημάτων στο συστημα*/
		@GET
		@Path("/addLesson")
		public List<Course> addCourseToDataBase(@QueryParam("courseName") String courseName,
				@QueryParam("courseHours") String courseHours) {
		
			Course course = new Course(courseName, courseHours);
			CourseDAO coursedao = new CourseDaoImpl();
			coursedao.save(course);
			List<Course> courses = coursedao.findAll();
		
			System.out.println("ΤΟ ΜΑΘΗΜΑ " + courseName +" ΚΑΤΑΧΩΡΗΘΗΚΕ");
			return courses;
		
		}
		
		/**Μέθοδος: Ανάθεση Μαθητών σε τμήματα*/
		@GET
		@Path("/students")
		public boolean studentAllocationToClasses(@QueryParam("schoolClass") String schoolClass,
												  @QueryParam("studentCode") int studentCode,
											      @QueryParam("studentclassCode") int studentclassCode) {
			
			List<StudentClass> studentclasses = getStudentClasses();
			List<StudentClass> studentclassesABC = new ArrayList<>();
			List<String> studentclassNames = new ArrayList<>();
			boolean result=false;
			int numberOfStudents=0;
			System.out.println("ΑΝΑΘΕΣΗ ΜΑΘΗΤΩΝ ΣΕ ΤΜΗΜΑΤΑ");
			System.out.println("EΠΙΛΕΞΤΕ ΤΑΞΗ ");
			
			boolean run=true;
			
			if(schoolClass.equals("A")) {
				result=true;
				System.out.println(" Έχετε επιλέξει Α Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("A")) {
					   studentclassesABC.add(stcl); 
					   
					   if(!studentclassNames.contains(stcl.getClassName())) {
							studentclassNames.add(stcl.getClassName()); 
						}
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassNames.size());
				
			}else if(schoolClass.equals("B")) {
				run=false;
				result=true;
				System.out.println(" Έχετε επιλέξει B Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("B")) {
					   studentclassesABC.add(stcl); 
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassesABC.size());
				
			}else if(schoolClass.equals("Γ")){
				run=false;
				result=true;
				System.out.println(" Έχετε επιλέξει Γ Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("Γ")) {
					   studentclassesABC.add(stcl); 
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassesABC.size());
				
			}else {
				System.out.println("Μη έγκυρη επιλογή ");
				System.out.println("EΠΙΛΕΞΤΕ ΤΑΞΗ ");
				result = false;
			}
			
			StudentDAO studentdao = new StudentDAOImpl();
			List<Student> students =  studentdao.findAll();
			int count=1;
			
			for(Student student: students) {
				System.out.println(count+" "+student.getFirstname()+ " "+ student.getLastname());
				count+=1;
			}
			
			int counter=1;
			int counter1=1;
			Student student;
			
			StudentClass studentClass;
			System.out.println("ΔΙΑΛΕΞΤΕ ΜΑΘΗΤΗ");
				student = students.get(studentCode-1);
				System.out.println("ΔΙΑΛΕΞΑΤΕ ΤΟ ΜΑΘΗΤΗ "+student.getFirstname()+" "+student.getLastname());
				System.out.println("ΔΙΑΛΕΞΤΕ ΤΜΗΜΑ");
		
				for(String stcl :studentclassNames) {  
				      System.out.println(counter1+ " "+ stcl); 
				      counter1+=1; 
				    }
				
				  studentClass = studentclasses.get(studentclassCode-1);
				  System.out.println("ΕΧΕΤΕ ΔΙΑΛΕΞΕΙ ΤΟ ΤΜΗΜΑ "+ studentClass.getClassName()); studentClass.addStudent(student); 
				  counter+=1;
				  studentClass.addStudent(student);
				  System.out.println("Ο ΜΑΘΗΤΗΣ "+ student.getFirstname()+ "  "+student.getLastname()+ 
						  " ΚΑΤΑΧΩΡΗΘΗΚΕ ΕΠΙΤΥΧΩΣ ΣΤΟ ΤΜΗΜΑ " + studentClass.getClassName());
				   addStudentToClass(studentClass);
				   numberOfStudents+=1;
				  System.out.println("ΔΙΑΛΕΞΤΕ ΜΑΘΗΤΗ");
				  counter1=1;
				  
				  count=1;
				  students.remove(student);
				  
				  System.out.println("ΑΠΟΜΕΝΟΝΤΕΣ ΜΑΘΗΤΕΣ ΓΙΑ ΚΑΤΑΧΩΡΗΣΗ");
				  for(Student student1: students) {
						System.out.println(count+" "+student1.getFirstname()+ " "+ student1.getLastname());
						count+=1;
					}
				  
				  System.out.println("ΔΙΑΛΕΞΤΕ NEO ΜΑΘΗΤΗ ΓΙΑ ΚΑΤΑΧΩΡΗΣΗ");
				  studentclassesABC = new ArrayList<>();
				  
				  //Έλεγχος αριθμού μαθητών
				  if(numberOfStudents>15) {
					  result=false;
					  System.out.println("Yπεράριθμοι μαθητές για το τμήμα " +studentClass.getClassName());
				  }
				  
			return result;
		}
		
		public void addStudentToClass(StudentClass studentClass) {
			
			StudentClassDAO studentclassdao = new StudentClassDAOImpl();
			studentclassdao.save1(studentClass);
		}
		
		/**Μέθοδος Επεξεργασιας Χρηστη --> αλλαγή συνθηματικών*/
		@GET
		@Produces (MediaType.APPLICATION_JSON)
		@Path("/editUser")
		public String editUser(@QueryParam("lastName") String lastName,
							   @QueryParam("newUserName") String newUserName,
							   @QueryParam("newPassword") String newPassword ) {	                       
			
			String password = null;
			ProfessorDAO profdao =new ProfessorDAOImpl();
			List <Professor> profs = profdao.findAll();
			
			for (Professor p : profs) {
				System.out.println("PROFESSOR NAME "+ p.getLastname());
				if(p.getLastname().equalsIgnoreCase(lastName)) {
					p.setPassword(newPassword);
					p.setUsername(newUserName);
					System.out.println("PROFESSOR NAME "+ p.getLastname());
					profdao.save(p);
					System.out.println("PROFESSOR NAME "+ p.getLastname());
					break;
				}
			}
			
			List <Professor> newprofs = profdao.findAll();
			for (Professor p :newprofs ) {
				if(p.getLastname().equalsIgnoreCase(lastName)) {
					password=p.getPassword();
					break;
				}
			}
			return password;
		
		}
		
		@GET
		@Path("/report")
		//@Produces (MediaType.APPLICATION_JSON)
		public boolean publishReport(@QueryParam("schoolClassCode") int schoolClassCode,
								  	 @QueryParam("studentCode") int studentCode,
								     @QueryParam("studentclassCode") int studentclassCode) {
			
			boolean result=false;
			int counter1 = 1;
			List<StudentClass> studentclasses = getStudentClasses();
			List<StudentClass> studentclassesABC = new ArrayList<>();
			List<String> studentclassNames = new ArrayList<>();
			List<Evaluation> evaluations = new ArrayList<>();
			System.out.println("Έχετε επιλέξει τη λειτουργία έκδοση αναφοράς");
		
			System.out.println("Eπιλέξτε τάξη");
			
			boolean run=true;
			for(int i=0; i<studentclassesABC.size(); i++) {
				studentclassesABC.remove(i);
			}
			if(schoolClassCode==1) {
				run=false;
				result=true;
				System.out.println(" Έχετε επιλέξει την τάξη Α Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("A")) {
						if(!studentclassesABC.contains(stcl)) {
							studentclassesABC.add(stcl); 
						}
						
						if(!studentclassNames.contains(stcl.getClassName())) {
							studentclassNames.add(stcl.getClassName()); 
						}
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassNames.size());
				
			}else if(schoolClassCode==2) {
				run=false;
				result=true;
				System.out.println(" Έχετε επιλέξει την τάξη B Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("B")) {
						if(!studentclassesABC.contains(stcl)) {
							   studentclassesABC.add(stcl); 
						}
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassNames.size());
				
			}else if(schoolClassCode==3){
				run=false;
				result=true;
				System.out.println(" Έχετε επιλέξει την τάξη Γ Γυμνασίου");
				for(StudentClass stcl :studentclasses) {
					if(stcl.getClassName().startsWith("Γ")) {
						if(!studentclassesABC.contains(stcl)) {
							   studentclassesABC.add(stcl); 
						}
					}
				}
				System.out.println("Aριθμός τμημάτων " +studentclassesABC.size());
				
			}else {
				System.out.println("Μη έγκυρη επιλογή ");
				System.out.println("EΠΙΛΕΞΤΕ ΤΑΞΗ ");
			}
		// }while(run);
			
			System.out.println("Τμήματα τάξης ");
			for(String stcl :studentclassNames) {  
			      System.out.println(counter1+ " "+ stcl); 
			      counter1+=1; 
			    }
			
			System.out.println("Επιλέξτε το τμήμα που θέλετε");
			
			@SuppressWarnings("resource")
			StudentClass studentclass = studentclassesABC.get(studentclassCode-1);
			System.out.println("Έχετε επιλέξει το τμήμα "+ studentclass.getClassName());
			
			StudentDAO studentdao = new StudentDAOImpl();  
			List<Student> students = studentdao.findAll();
			
			EvaluationDAO evaluationdao = new EvaluationDAOImpl();				
			List<Evaluation> evaluations1 = evaluationdao.findAll();
			
			System.out.println("Αξιολόγηση "+evaluations1.size());
			for(Evaluation eval :evaluations1) {
				System.out.println("Βαθμολογία "+eval.getStudent().getFirstname()+ " " + eval.getStudent().getLastname() +"  "+eval.getGrade());
			}
			
			int counter=1;
			for(Student student :students) {
				System.out.println(counter + " " +student.getFirstname() + " " + student.getLastname());
				counter++;
			}
			System.out.println("Έπιλέξτε μαθητή για έκδοση αναφοράς "); 
			Student student = students.get(studentCode-1);
			System.out.println("Έχετε επιλέξει το μαθητή: "+ student.getFirstname()+ " "+student.getLastname());
			System.out.println("Θέλετε έκδοση αναφοράς; y n");
			String option3 = "y";
			if(option3.equalsIgnoreCase("y")) {
				evaluations = student.getEvaluations();
				System.out.println("Υπάρχουν "+ evaluations.size() +" " + "Αξιολογήσεις για τον μαθητή");
			 
			}
			
			if(evaluations.size()==0) {
				System.out.println("Δεν υπάρχουν ακόμη αξιολογήσεις για τον μαθητή");
			}else {
			    System.out.println(" Η αξιολόγηση για το μαθητή "+ student.getFirstname()+" "+student.getLastname() +" είναι: ");
			    for(int i=0; i<evaluations.size(); i++) {
			    	System.out.println(" Μάθημα: "+evaluations.get(i).getCourse().getCourseName()+" με βαθμολογία " + evaluations.get(i).getGrade());
			    }
			}
			return result;	
		}
}
