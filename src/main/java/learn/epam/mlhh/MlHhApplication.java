package learn.epam.mlhh;

import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.entity.Log;
import learn.epam.mlhh.entity.Users;
import learn.epam.mlhh.service.CandidateService;
import learn.epam.mlhh.service.LogService;
import learn.epam.mlhh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Date;
import java.sql.Time;


@SpringBootApplication
public class MlHhApplication {
	private final 	static Logger logger = Logger.getLogger(MlHhApplication.class);

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	public static void main(String[] args) {
		SpringApplication.run(MlHhApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods(){
		logger.info("Add new candidates");
		Candidate candidate = new Candidate();
		candidate.setName("Smith");
		candidate.setAge(25);
		candidate.setGender("М");
		candidate.setRegion("Gorky");
		candidate.setSalary(50.000);
		candidate.setDeveloper("Java Developer");
		candidate.setExperience(1);
		candidate.setKeyWord("Spring");
		candidateService.createCandidate(candidate);

		Candidate candidate1 = new Candidate();
		candidate1.setName("John");
		candidate1.setAge(30);
		candidate1.setGender("М");
		candidate1.setRegion("Gorky");
		candidate1.setSalary(500.000);
		candidate1.setDeveloper("Java Developer");
		candidate1.setExperience(3);
		candidate1.setKeyWord("Spring");
		candidateService.createCandidate(candidate1);

		Candidate candidate2 = new Candidate();
		candidate2.setName("Poul");
		candidate2.setAge(40);
		candidate2.setGender("М");
		candidate2.setRegion("Gorky");
		candidate2.setSalary(1000.000);
		candidate2.setDeveloper("Java Developer");
		candidate2.setExperience(5);
		candidate2.setKeyWord("Spring");
		candidateService.createCandidate(candidate2);


		Candidate candidate3 = new Candidate();
		candidate3.setName("Anna");
		candidate3.setAge(25);
		candidate3.setGender("Ж");
		candidate3.setRegion("Mos");
		candidate3.setSalary(500.000);
		candidate3.setDeveloper("Java Developer");
		candidate3.setExperience(3);
		candidate3.setKeyWord("Spring");
		candidateService.createCandidate(candidate3);

		Candidate newCandidate = new Candidate();
		newCandidate.setName("FIO");
		newCandidate.setAge(20);
		newCandidate.setGender("М");
		newCandidate.setRegion("Gorky");
		newCandidate.setSalary(5000.0);
		newCandidate.setDeveloper("");
		newCandidate.setExperience(0);
		newCandidate.setKeyWord("-");
		candidateService.createCandidate(newCandidate);

		Users user = new Users();
		user.setUserName("Admin");
		user.setUserPassword("password123");
		userService.createUser(user);


		Log log = new Log();
		log.setDate(Date.valueOf("2018-01-01"));
		log.setTime(Time.valueOf("11:12:01"));
		log.setClassName("Log");
		log.setMessage("message");
		log.setStatus("status");
		logService.createLog(log);

		/*candidateService.findAll().forEach(it-> System.out.println(it));

		candidateService.findAllByName("Smith").forEach(it-> System.out.println(it));*/
	}
}
