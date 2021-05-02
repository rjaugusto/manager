package br.com.smartfinances.manager.repository;

import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
