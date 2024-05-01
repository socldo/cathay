package interview.com.cathayunited;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableScheduling
public class CathayunitedApplication {

	public static void main(String[] args) {
		SpringApplication.run(CathayunitedApplication.class, args);
	}

}
