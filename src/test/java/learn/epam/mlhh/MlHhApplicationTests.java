package learn.epam.mlhh;

import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.entity.Log;
import learn.epam.mlhh.entity.Users;
import learn.epam.mlhh.service.CandidateService;
import learn.epam.mlhh.service.LogService;
import learn.epam.mlhh.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlHhApplicationTests {

	private final 	static Logger logger = Logger.getLogger(MlHhApplication.class);

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	@Test
	public void contextLoads() {
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
