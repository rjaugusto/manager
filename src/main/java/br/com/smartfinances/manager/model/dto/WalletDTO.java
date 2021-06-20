package br.com.smartfinances.manager.model.dto;

import br.com.smartfinances.manager.model.Asset;
import br.com.smartfinances.manager.model.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WalletDTO {

    @Null
    private Long id;

    @NotNull
    private String name;

    @Null
    private List<Asset> assets = new ArrayList<>();

    public WalletDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
