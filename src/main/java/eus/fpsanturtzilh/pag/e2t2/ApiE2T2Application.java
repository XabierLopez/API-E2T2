package eus.fpsanturtzilh.pag.e2t2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiE2T2Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiE2T2Application.class, args);
	}

}
