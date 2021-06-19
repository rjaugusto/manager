package br.com.smartfinances.manager.repository;

import br.com.smartfinances.manager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Optional<Wallet> findByName(String name);
}
