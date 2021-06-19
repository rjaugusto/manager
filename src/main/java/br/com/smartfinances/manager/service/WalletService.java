package br.com.smartfinances.manager.service;

import br.com.smartfinances.manager.exception.WalletIsAlreadyRegisteredException;
import br.com.smartfinances.manager.mapper.WalletMapper;
import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.model.dto.WalletDTO;
import br.com.smartfinances.manager.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper = WalletMapper.INSTANCE;

    @Autowired
    public WalletService(final WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }


    public WalletDTO createWallet(WalletDTO walletDTO) throws WalletIsAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(walletDTO);
        Wallet wallet = walletMapper.toModel(walletDTO);
        Wallet savedBeer = walletRepository.save(wallet);
        return walletMapper.toDTO(savedBeer);
    }
    
    public void verifyIfIsAlreadyRegistered(WalletDTO walletDTO) throws WalletIsAlreadyRegisteredException {
        Optional<Wallet> byName = walletRepository.findByName(walletDTO.getName());

        if (byName.isPresent()) {
            throw new WalletIsAlreadyRegisteredException(walletDTO.getName());
        }
    }

}
