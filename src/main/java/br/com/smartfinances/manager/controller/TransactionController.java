package br.com.smartfinances.manager.controller;

import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "transaction")
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Transaction>> listAll(){
        List<Transaction> all = transactionRepository.findAll();
        System.out.println(all);
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public void salva(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
