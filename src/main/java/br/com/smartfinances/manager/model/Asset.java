package br.com.smartfinances.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Asset implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy="asset")
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    public Asset(String name, Category category, Wallet wallet) {
        this.name = name;
        this.category = category;
        this.wallet = wallet;
    }

    public Asset() {

    }
}
