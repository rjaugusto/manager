package br.com.smartfinances.manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
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
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="wallet")
    private List<Transaction> transactions = new ArrayList<>();

    public Wallet() {
    }

    public Wallet(String name) {
    }

    public Wallet(Long id, String name, List<Transaction> transactions) {
    }
}
