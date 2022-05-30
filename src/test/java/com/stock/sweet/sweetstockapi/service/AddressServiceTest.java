package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.repository.AddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AddressService.class})
class AddressServiceTest {

    @Autowired
    private AddressService service ;

    @MockBean
    private AddressRepository rep;

    @Test
    @DisplayName("Should return all address")
    void getAllAdresses() {
        Address c1 = mock(Address.class);
        Address c2 = mock(Address.class);
        List<Address> listMock= List.of(c1,c2);
        when(rep.findAll()).thenReturn(listMock);
        assertEquals(listMock, service.getAllAdresses());
    }

    @Test
    @DisplayName("Should return null ")
    void getAllAdressesError() {
        when(rep.findAll()).thenReturn(null);
        assertEquals(null, service.getAllAdresses());
    }

    @Test
    void findAddressByUuid() {
    }

    @Test
    void updateAddress() {
    }

    @Test
    void deleteAddress() {
    }
}