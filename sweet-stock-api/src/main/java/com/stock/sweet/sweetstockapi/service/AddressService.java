package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
