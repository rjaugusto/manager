package br.com.smartfinances.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WalletIsAlreadyRegisteredException extends Throwable {

    public WalletIsAlreadyRegisteredException(String accountName) {
        super(String.format("Account with name %s already registered in the system.", accountName));
    }
}


