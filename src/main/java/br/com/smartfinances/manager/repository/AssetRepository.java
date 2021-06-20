package br.com.smartfinances.manager.repository;

import br.com.smartfinances.manager.model.Asset;
import br.com.smartfinances.manager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    List<Asset> findByWallet(Wallet wallet);
}
