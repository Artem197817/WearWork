package workwear.workwearclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class WorkwearclientApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(WorkwearclientApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(WorkwearclientApplication.class);
		builder.headless(false).run(args);
	}
}
