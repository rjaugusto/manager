package br.com.smartfinances.manager.builder;


import br.com.smartfinances.manager.model.Transaction;
import br.com.smartfinances.manager.model.dto.AccountDTO;
import lombok.Builder;

import java.util.Arrays;
import java.util.List;

@Builder
public class AccountDTOBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Banco do Brasil";

    @Builder.Default
    private List<Transaction> transactions = Arrays.asList(new Transaction());

    public AccountDTO toAccountDTO() {
        return new AccountDTO(id, name);
    }

}
