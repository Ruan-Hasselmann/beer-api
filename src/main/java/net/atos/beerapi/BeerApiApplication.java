package net.atos.beerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("net.atos.beerapi.repository")
@EntityScan("net.atos.beerapi.model")
public class BeerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerApiApplication.class, args);
	}

}
