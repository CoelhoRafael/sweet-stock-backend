package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.AddressRequest;
import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAdresses() {
        return addressRepository.findAll();
    }

    public Address findAddressByUuid(String uuid) throws Exception {
        return addressRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }

    public Address updateAddress(String uuid, AddressRequest addressRequest) throws Exception {
        Address addressToUpdate = findAddressByUuid(uuid);

        if (addressToUpdate == null) {
            return null;
        }

        addressToUpdate.setStreet(addressRequest.getStreet());
        addressToUpdate.setNumber(addressRequest.getNumber());
        addressToUpdate.setComplement(addressRequest.getComplement());
        addressToUpdate.setCity(addressRequest.getCity());
        addressToUpdate.setState(addressRequest.getState());
        addressToUpdate.setNeighborhood(addressRequest.getNeighborhood());

        addressRepository.save(addressToUpdate);

        return addressToUpdate;
    }

    public Address deleteAddress(String uuid) throws Exception {
        Address addressToDelete = findAddressByUuid(uuid);

        if (addressToDelete == null) {
            return null;
        }

        addressRepository.delete(addressToDelete);
        return addressToDelete;
    }
}
