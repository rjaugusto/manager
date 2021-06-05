package br.com.smartfinances.manager.service;

import br.com.smartfinances.manager.exception.AccountIsAlreadyRegisteredException;
import br.com.smartfinances.manager.mapper.AccountMapper;
import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.model.dto.AccountDTO;
import br.com.smartfinances.manager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Autowired
    public AccountService(final AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public AccountDTO createAccount(AccountDTO accountDTO) throws AccountIsAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(accountDTO);
        Account account = accountMapper.toModel(accountDTO);
        Account savedBeer = accountRepository.save(account);
        return accountMapper.toDTO(savedBeer);
    }
    
    public void verifyIfIsAlreadyRegistered(AccountDTO accountDTO) throws AccountIsAlreadyRegisteredException {
        Optional<Account> byName = accountRepository.findByName(accountDTO.getName());

        if (byName.isPresent()) {
            throw new AccountIsAlreadyRegisteredException(accountDTO.getName());
        }
    }

}
