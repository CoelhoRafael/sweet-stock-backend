package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByUuid(String uuid);
}