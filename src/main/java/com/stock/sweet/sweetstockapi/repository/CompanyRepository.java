package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByUuid(String uuid);

    @Query("select c from Company c where c.uuid = ?1")
    Company findCompanyByUuid(String uuid);
    Optional<Company> findByAssociateCode(String associateCode);

    @Query("select c from Company c where c.activated = true")
    List<Company> findAllActiveCompanies();


}
