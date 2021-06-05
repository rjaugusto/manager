package br.com.smartfinances.manager;

import br.com.smartfinances.manager.controller.TransactionController;
import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.model.enums.TransctionType;
import br.com.smartfinances.manager.repository.AccountRepository;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Access;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ManagerApplication implements CommandLineRunner{

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {

		SpringApplication.run(ManagerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		for (int i=1; i <= 100;i++){
			var account = new Account();
			account.setName("Account"+i);
			accountRepository.save(account);
		}

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/account").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
