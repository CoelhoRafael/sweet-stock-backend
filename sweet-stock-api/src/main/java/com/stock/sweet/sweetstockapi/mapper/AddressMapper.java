package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.AddressRequest;
import com.stock.sweet.sweetstockapi.dto.response.AddressResponse;
import com.stock.sweet.sweetstockapi.model.Address;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddressMapper {

    public Address convertRequestToModel(AddressRequest addressRequest){
        return new Address(
                null,
                UUID.randomUUID().toString(),
                addressRequest.getStreet(),
                addressRequest.getNumber(),
                addressRequest.getComplement(),
                addressRequest.getCity(),
                addressRequest.getState(),
                addressRequest.getNeighborhood()
        );
    }

    public AddressResponse convertModelToResponse(Address address){
        return new AddressResponse(
                address.getUuid(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood()
        );
    }
}
