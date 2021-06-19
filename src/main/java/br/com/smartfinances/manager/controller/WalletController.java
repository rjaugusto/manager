package br.com.smartfinances.manager.controller;


import br.com.smartfinances.manager.exception.WalletIsAlreadyRegisteredException;
import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.repository.WalletRepository;
import br.com.smartfinances.manager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.smartfinances.manager.model.dto.WalletDTO;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "wallet")
public class WalletController {

    private final WalletRepository walletRepository;
    private final WalletService walletService;

    @Autowired
    public WalletController(final WalletRepository walletRepository, final WalletService walletService){
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/list")
    public List<Wallet> listAll(){
        return  walletRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDTO createAccout(@Valid WalletDTO walletDTO) throws WalletIsAlreadyRegisteredException {
        return walletService.createWallet(walletDTO);
    }

}
