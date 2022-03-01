package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.AddressRequest;
import com.stock.sweet.sweetstockapi.dto.response.AddressResponse;
import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> getAllAdresses() {
        return addressMapper.convertModelListToResponseList(
                addressService.getAllAdresses()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getAddressByUuid(@PathVariable String uuid) throws Exception {
        return addressMapper.convertModelToResponse(
                addressService.findAddressByUuid(uuid)
        );
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updateAddress(@PathVariable String uuid, @RequestBody AddressRequest body) throws Exception {
        return addressMapper.convertModelToResponse(
                addressService.updateAddress(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AddressResponse deleteAddress(@PathVariable String uuid) throws Exception {
        return addressMapper.convertModelToResponse(
                addressService.deleteAddress(uuid)
        );
    }

}