package br.com.smartfinances.manager.model;

import br.com.smartfinances.manager.model.enums.TransctionType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateTransaction;
    private String assetName;
    private BigDecimal price;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private TransctionType transctionType;

    @ManyToOne
    private Account account;

    public Transaction(Long id, LocalDate dateTransaction, String assetName, BigDecimal price, Integer amount, TransctionType transctionType, Account account) {
        this.id = id;
        this.dateTransaction = dateTransaction;
        this.assetName = assetName;
        this.price = price;
        this.amount = amount;
        this.transctionType = transctionType;
        this.account = account;
    }
}
