package br.com.smartfinances.manager.service;

import br.com.smartfinances.manager.builder.AccountDTOBuilder;
import br.com.smartfinances.manager.mapper.AccountMapper;
import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.model.dto.AccountDTO;
import br.com.smartfinances.manager.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.smartfinances.manager.exception.*;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountMapper accountMapper = AccountMapper.INSTANCE;

    @InjectMocks
    private AccountService accountService;

    @Test
    void whenAccountInformedThenItShuldBeCreated() throws AccountIsAlreadyRegisteredException{
        //given // dado
        AccountDTO expectedAccountDTO = AccountDTOBuilder.builder().build().toAccountDTO();
        Account expectedSavedAccount = accountMapper.toModel(expectedAccountDTO);

        //when
        when(accountRepository.findByName(expectedAccountDTO.getName())).thenReturn(Optional.empty());
        when(accountRepository.save(expectedSavedAccount)).thenReturn(expectedSavedAccount);

        //then
        AccountDTO createdAccountDTO = accountService.createAccount(expectedAccountDTO);
        assertThat(createdAccountDTO.getId(), is(equalTo(expectedAccountDTO.getId())));

    }

    @Test
    void whenAlreadyRegistredAccountInformedThenAnExpetionShouldBeThrown() {
        // given
        AccountDTO expectedAccountDTO = AccountDTOBuilder.builder().build().toAccountDTO();
        Account duplicatedBeer = accountMapper.toModel(expectedAccountDTO);

        // when
        when(accountRepository.findByName(expectedAccountDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        // then
        assertThrows(AccountIsAlreadyRegisteredException.class, () -> accountService.createAccount(expectedAccountDTO));
    }
}
