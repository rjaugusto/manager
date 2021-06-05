package br.com.smartfinances.manager.mapper;

import br.com.smartfinances.manager.model.Account;
import br.com.smartfinances.manager.model.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toModel(AccountDTO beerDTO);

    AccountDTO toDTO(Account beer);
}



