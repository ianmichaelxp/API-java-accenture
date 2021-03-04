package br.org.javangers.bankline.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.org.javangers.bankline.service.DBService;


@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		
		System.out.println("############STRATEGY: " + strategy);
		
		if(!strategy.equals("create")) {
			return false;
		}
		dbService.instanciateDatabase();
		return true;
	}
	
}
