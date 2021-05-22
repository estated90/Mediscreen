package web.mediscreen.diabetalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class DiabetalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabetalertApplication.class, args);
	}

}
