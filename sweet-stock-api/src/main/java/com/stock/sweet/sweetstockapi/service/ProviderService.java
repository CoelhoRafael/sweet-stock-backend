package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Provider;
import com.stock.sweet.sweetstockapi.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public Provider createProvider(Provider provider){
        return providerRepository.save(provider);
    }

    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }
}
