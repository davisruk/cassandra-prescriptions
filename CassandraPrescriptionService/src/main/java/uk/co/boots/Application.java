package uk.co.boots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile({"!test", "prod", "dev"})
@SpringBootApplication
public class Application{
	
	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
}