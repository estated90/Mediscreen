package web.mediscreen.personalinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nicolas
 *
 */
@SpringBootApplication
@EnableSwagger2
public class PersonalInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalInfoApplication.class, args);
	}

}
