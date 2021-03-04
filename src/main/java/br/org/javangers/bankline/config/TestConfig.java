package br.org.javangers.bankline.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.org.javangers.bankline.service.DBService;


@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbService.instanciateDatabase();
		return true;
	}
}
