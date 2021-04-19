package web.historic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("web.historic")
@EnableFeignClients
public class HistoricApplication {

    public static void main(String[] args) {
	SpringApplication.run(HistoricApplication.class, args);
    }

}
