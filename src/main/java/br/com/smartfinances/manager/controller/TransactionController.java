package br.com.smartfinances.manager.controller;

import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(path = "/")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> listAll(){

        var transactionList = transactionRepository.findAll();

        if (transactionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<Transaction>>(transactionList, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/transactions/{id}")
    public ResponseEntity<Transaction> listOne(@PathVariable(value = "id") Long id){

        var trasactionO = transactionRepository.findById(id);

        if (trasactionO.isPresent()){
            return new ResponseEntity<Transaction>(trasactionO.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/transactions/{id}")
    public ResponseEntity<Transaction> salveTransaction(@RequestBody @Valid Transaction transaction){
        return new ResponseEntity<Transaction>(transactionRepository.save(transaction),HttpStatus.CREATED);
    }

    public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") Long id,
                                                         @RequestBody @Valid Transaction transaction){

        var trasactionO = transactionRepository.findById(id);

        if (trasactionO.isPresent()){
            return new ResponseEntity<Transaction>(transactionRepository.save(transaction),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
