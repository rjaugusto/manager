package br.com.smartfinances.manager.controller;

import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public List<Transaction> listAll(){
        return transactionRepository.findAll();
    }

    @PostMapping
    public void salva(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
