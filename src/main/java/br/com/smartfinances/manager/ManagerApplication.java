package br.com.smartfinances.manager;

import br.com.smartfinances.manager.model.Asset;
import br.com.smartfinances.manager.model.Category;
import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.model.enums.TransctionType;
import br.com.smartfinances.manager.repository.AssetRepository;
import br.com.smartfinances.manager.repository.WalletRepository;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootApplication
public class ManagerApplication implements CommandLineRunner{

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	WalletRepository walletRepository;
	@Autowired
	AssetRepository assetRepository;

	@Autowired
	Wallet wallet;

	@Autowired
	Wallet walletEasy;

	public static void main(String[] args) {

		SpringApplication.run(ManagerApplication.class, args);
	}


	@Override
	public void run(String... args){

		//Wallet wallet = new Wallet("Carteira Pessoal");
		//Wallet walletEasy = new Wallet("Carteira EasyInvest");

		wallet.setName("Carteira Pessoal");
		walletEasy.setName("Carteira EasyInvest");

		walletRepository.save(wallet);
		walletRepository.save(walletEasy);

		Asset bitcoin = new Asset("BitCoin", Category.CryptoCurrency,wallet);
		Asset bbas3 = new Asset("BBAS3",Category.Stock,walletEasy);
		assetRepository.save(bitcoin);
		assetRepository.save(bbas3);

		Transaction transaction = new Transaction(LocalDate.now(),new BigDecimal(50.62),100,TransctionType.BUY,bitcoin);
		transactionRepository.save(transaction);

		Transaction transactionEasy = new Transaction(LocalDate.now(),new BigDecimal(50.62),100,TransctionType.BUY,bbas3);
		transactionRepository.save(transactionEasy);

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
