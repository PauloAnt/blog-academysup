package br.com.paulo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.paulo.repositories")
@ComponentScan(basePackages = "br.com.paulo")
public class AcademysupApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademysupApiApplication.class, args);
	}

}
