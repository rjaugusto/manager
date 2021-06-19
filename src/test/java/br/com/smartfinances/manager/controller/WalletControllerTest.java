package br.com.smartfinances.manager.controller;

import br.com.smartfinances.manager.builder.WalletDTOBuilder;
import br.com.smartfinances.manager.exception.WalletIsAlreadyRegisteredException;
import br.com.smartfinances.manager.model.dto.WalletDTO;
import br.com.smartfinances.manager.service.WalletService;
import br.com.smartfinances.manager.utils.JsonConvertionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {

    private static final String ACCOUNT_API_URL_PATH = "/account";
    private static final long VALID_BEER_ID = 1L;
    private static final long INVALID_BEER_ID = 2l;
    private static final String BEER_API_SUBPATH_INCREMENT_URL = "/increment";
    private static final String BEER_API_SUBPATH_DECREMENT_URL = "/decrement";

    private MockMvc mockMvc;

    @InjectMocks
    private WalletController accountController;

    @Mock
    private WalletService walletService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAAccountIsCreated() throws Exception, WalletIsAlreadyRegisteredException {
        // given
        WalletDTO walletDTO = WalletDTOBuilder.builder().build().toAccountDTO();;

        // when
        when(walletService.createWallet(walletDTO)).thenReturn(walletDTO);

        // then
        mockMvc.perform(post(ACCOUNT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(walletDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(walletDTO.getName())))
                .andExpect(jsonPath("$.transactions", is(walletDTO.getTransactions())));

    }

    @Test
    void whenPOSTIsCalledWithoutAAttributeThenAValidationErrorIsThrow() throws Exception {
        // given
        WalletDTO walletDTO = WalletDTOBuilder.builder().build().toAccountDTO();;
        walletDTO.setName(null);

        // then
        mockMvc.perform(post(ACCOUNT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(walletDTO)))
                .andExpect(status().isBadRequest());

    }


}
