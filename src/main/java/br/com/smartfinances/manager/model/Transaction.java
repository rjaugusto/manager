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
    private BigDecimal price;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private TransctionType transctionType;

    @ManyToOne
    @JoinColumn(name="asset_id")
    private Asset asset;

    public Transaction() {
    }

    public Transaction(LocalDate dateTransaction, BigDecimal price, Integer amount, TransctionType transctionType, Asset asset) {
        this.dateTransaction = dateTransaction;
        this.price = price;
        this.amount = amount;
        this.transctionType = transctionType;
        this.asset = asset;
    }
}
