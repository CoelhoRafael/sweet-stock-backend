package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>{
}
