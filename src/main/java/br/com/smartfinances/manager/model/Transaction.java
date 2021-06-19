package br.com.smartfinances.manager.model;

import br.com.smartfinances.manager.model.enums.TransctionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    public Transaction(Long id, LocalDate dateTransaction, String assetName, BigDecimal price, Integer amount, TransctionType transctionType, Wallet wallet) {
        this.id = id;
        this.dateTransaction = dateTransaction;
        this.assetName = assetName;
        this.price = price;
        this.amount = amount;
        this.transctionType = transctionType;
        this.wallet = wallet;
    }

    public Transaction() {
    }
}
