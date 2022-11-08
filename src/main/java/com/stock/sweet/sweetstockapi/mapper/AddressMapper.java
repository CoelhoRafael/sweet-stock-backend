package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.AddressRequest;
import com.stock.sweet.sweetstockapi.dto.response.address.AddressResponse;
import com.stock.sweet.sweetstockapi.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public List<AddressResponse> convertModelListToResponseList(List<Address> adresses) {
        return adresses.stream().map(a -> {
            return new AddressResponse(
                    a.getUuid(),
                    a.getStreet(),
                    a.getNumber(),
                    a.getComplement(),
                    a.getCity(),
                    a.getState(),
                    a.getNeighborhood()
            );
        }).collect(Collectors.toList());
    }

    public Address convertRequestToModel(AddressRequest addressRequest) {
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

    public AddressResponse convertModelToResponse(Address address) {
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
