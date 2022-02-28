package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.model.Address;
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

    public Provider convertRequestToModel(ProviderRequest providerRequest){
        return new Provider(
                null,
                UUID.randomUUID().toString(),
                providerRequest.getName(),
                providerRequest.getCnpj(),
                providerRequest.getAverageTimeForDeliveryInDays(),
                addressMapper.convertRequestToModel(providerRequest.getAddressRequest())
        );
    }

    public ProviderResponse convertModelToResponse(Provider provider){
        return new ProviderResponse(
                provider.getUuid(),
                provider.getName(),
                provider.getCnpj(),
                provider.getAverageTimeForDeliveryInDays(),
                addressMapper.convertModelToResponse(provider.getAddress())
        );
    }

    public List<ProviderResponse> convertModelListToResponseList(List<Provider> providers){
        return providers.stream().map(p ->{
          return new ProviderResponse(
                  p.getUuid(),
                  p.getName(),
                  p.getCnpj(),
                  p.getAverageTimeForDeliveryInDays(),
                  addressMapper.convertModelToResponse(p.getAddress())
          );
        }).collect(Collectors.toList());
    }

}
