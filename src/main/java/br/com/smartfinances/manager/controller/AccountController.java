package br.com.smartfinances.manager.controller;


import br.com.smartfinances.manager.exception.AccountIsAlreadyRegisteredException;
import br.com.smartfinances.manager.mapper.AccountMapper;
import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.repository.AccountRepository;
import br.com.smartfinances.manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.smartfinances.manager.model.dto.AccountDTO;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountRepository accountRepository,final AccountService accountService){
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/list")
    public List<Account> listAll(){
        return  accountRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccout(@Valid AccountDTO accountDTO) throws AccountIsAlreadyRegisteredException {
        return accountService.createAccount(accountDTO);
    }

}
