package learn.epam.mlhh;

import learn.epam.mlhh.controllers.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MlHhApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlHhApplication.class, args);
		Database.connectDatabase();
	}
}
