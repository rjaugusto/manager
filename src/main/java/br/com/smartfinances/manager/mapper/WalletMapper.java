package br.com.smartfinances.manager.mapper;

import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.model.dto.WalletDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    Wallet toModel(WalletDTO beerDTO);

    WalletDTO toDTO(Wallet beer);
}



