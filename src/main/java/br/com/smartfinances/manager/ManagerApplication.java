package br.com.smartfinances.manager;

import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.repository.WalletRepository;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ManagerApplication implements CommandLineRunner{

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	WalletRepository walletRepository;

	public static void main(String[] args) {

		SpringApplication.run(ManagerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		for (int i=1; i <= 100;i++){
			var wallet = new Wallet();
			wallet.setName("Wallet - "+Math.random());
			walletRepository.save(wallet);
		}

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/wallet").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
