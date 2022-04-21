package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.OutStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutStockRepository extends JpaRepository<OutStock, Integer> {
    List<OutStock> findAllByIdCompany(String idCompany);
}
