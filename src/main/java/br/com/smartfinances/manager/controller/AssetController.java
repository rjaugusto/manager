package br.com.smartfinances.manager.controller;

import br.com.smartfinances.manager.model.Asset;
import br.com.smartfinances.manager.model.Wallet;
import br.com.smartfinances.manager.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "asset")
public class AssetController {

    @Autowired
    AssetRepository assetRepository;

    @GetMapping(path = "/list")
    public List<Asset> list(){
        return assetRepository.findAll();
    }

    @GetMapping(path = "/list/wallet")
    public List<Asset> list(Wallet wallet){
        return assetRepository.findByWallet(wallet);
    }
}
