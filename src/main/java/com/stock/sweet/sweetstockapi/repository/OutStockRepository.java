package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.OutStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OutStockRepository extends JpaRepository<OutStock, Integer> {
    List<OutStock> findAllByIdCompany(String idCompany);

    @Query("select o from OutStock o where o.idCompany = ?1 and o.date between ?2 and ?3")
    Integer allItensSold (String uuid, LocalDate date1, LocalDate date2);
}
