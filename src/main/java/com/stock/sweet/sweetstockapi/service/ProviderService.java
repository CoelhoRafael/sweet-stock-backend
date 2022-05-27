package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Provider;
import com.stock.sweet.sweetstockapi.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private AddressMapper addressMapper;

    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public List<Provider> getAllProviders(String uuid) {
        return providerRepository.findAllByCompanyUuid(uuid);
    }

    public Provider findProviderByUuid(String uuid) throws Exception {
        return providerRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }

    public Provider updateProvider(String uuid, ProviderRequest providerRequest) throws Exception {
        Provider providerToUpdate = findProviderByUuid(uuid);

        if (providerToUpdate == null) {
            return null;
        }

        providerToUpdate.setName(providerRequest.getName());
        providerToUpdate.setCnpj(providerRequest.getCnpj());
        providerToUpdate.setAverageTimeForDeliveryInDays(providerRequest.getAverageTimeForDeliveryInDays());

        providerRepository.save(providerToUpdate);

        return providerToUpdate;
    }

    public Provider deleteProvider(String uuid) throws Exception {
        Provider providerToDelete = findProviderByUuid(uuid);

        if (providerToDelete == null) {
            return null;
        }

        providerRepository.delete(providerToDelete);
        return providerToDelete;
    }
}
