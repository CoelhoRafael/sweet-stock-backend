package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Optional<Provider> findByUuid(String uuid);

    List<Provider> findAllByCompanyUuid(String uuidCompany);
}
