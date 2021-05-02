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

import javax.persistence.Access;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ManagerApplication implements CommandLineRunner {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Account accout = new Account(null,"EasyInvest");
		Account salva = accountRepository.save(accout);
		Transaction transaction = new Transaction(null, LocalDate.now(),"BBAS3",new BigDecimal(30.00),200, TransctionType.BUY,salva);
		transactionRepository.save(transaction);
	}
}
