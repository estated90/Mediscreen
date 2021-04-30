package web.mediscreen.historic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("web.historic")
@EnableSwagger2
@EnableFeignClients
public class HistoricApplication {

    public static void main(String[] args) {
	SpringApplication.run(HistoricApplication.class, args);
    }

}
