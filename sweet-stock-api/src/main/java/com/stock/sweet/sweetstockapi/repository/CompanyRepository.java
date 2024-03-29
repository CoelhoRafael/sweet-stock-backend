package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
     Optional<Company> findByUuid(String uuid);
     List<Company> findCompaniesByOpenHourAfterAndCloseHourBefore (LocalTime openHour, LocalTime closeHour);
}
