package br.com.smartfinances.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="account")
    private List<Transaction> transactions;

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}
