package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.ProviderResponseIngredients;
import com.stock.sweet.sweetstockapi.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProviderMapper {

    @Autowired
    private AddressMapper addressMapper;

    public Provider convertRequestToModel(ProviderRequest providerRequest, String uuidCompany) {
        return new Provider(
                null,
                UUID.randomUUID().toString(),
                providerRequest.getName(),
                providerRequest.getCnpj(),
                providerRequest.getEmail(),
                providerRequest.getTelephone(),
                providerRequest.getAverageTimeForDeliveryInDays(),
                uuidCompany
        );
    }

    public ProviderResponse convertModelToResponse(Provider provider) {
        return new ProviderResponse(
                provider.getId(),
                provider.getUuid(),
                provider.getName(),
                provider.getCnpj(),
                provider.getEmail(),
                provider.getTelephone(),
                provider.getAverageTimeForDeliveryInDays());
    }

    public List<ProviderResponse> convertModelListToResponseList(List<Provider> providers) {
        return providers.stream().map(p -> {
            return new ProviderResponse(
                    p.getId(),
                    p.getUuid(),
                    p.getName(),
                    p.getCnpj(),
                    p.getEmail(),
                    p.getTelephone(),
                    p.getAverageTimeForDeliveryInDays());
        }).collect(Collectors.toList());
    }
    public List<ProviderResponseIngredients> convertModelListToResponseListIngredients(List<Provider> providers) {
        return providers.stream().map(p -> {
            return new ProviderResponseIngredients(
              p.getUuid(),
              p.getName() );
        }).collect(Collectors.toList());
    }



}
