package br.org.javangers.bankline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankLineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankLineApplication.class, args);
		System.out.println("✔️ Running!!");
	}

}
