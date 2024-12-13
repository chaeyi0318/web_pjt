package com.project.partyparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PartyPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyPartyApplication.class, args);
	}

}
