package br.com.smartfinances.manager.service;

import br.com.smartfinances.manager.builder.WalletDTOBuilder;
import br.com.smartfinances.manager.mapper.WalletMapper;
import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.model.dto.WalletDTO;
import br.com.smartfinances.manager.repository.WalletRepository;
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
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    private WalletMapper walletMapper = WalletMapper.INSTANCE;

    @InjectMocks
    private WalletService walletService;

    @Test
    void whenAccountInformedThenItShuldBeCreated() throws WalletIsAlreadyRegisteredException {
        //given // dado
        WalletDTO expectedWalletDTO = WalletDTOBuilder.builder().build().toAccountDTO();
        Wallet expectedSavedWallet = walletMapper.toModel(expectedWalletDTO);

        //when
        when(walletRepository.findByName(expectedWalletDTO.getName())).thenReturn(Optional.empty());
        when(walletRepository.save(expectedSavedWallet)).thenReturn(expectedSavedWallet);

        //then
        WalletDTO createdWalletDTO = walletService.createWallet(expectedWalletDTO);
        assertThat(createdWalletDTO.getId(), is(equalTo(expectedWalletDTO.getId())));

    }

    @Test
    void whenAlreadyRegistredAccountInformedThenAnExpetionShouldBeThrown() {
        // given
        WalletDTO expectedWalletDTO = WalletDTOBuilder.builder().build().toAccountDTO();
        Wallet duplicatedBeer = walletMapper.toModel(expectedWalletDTO);

        // when
        when(walletRepository.findByName(expectedWalletDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        // then
        assertThrows(WalletIsAlreadyRegisteredException.class, () -> walletService.createWallet(expectedWalletDTO));
    }
}
