package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.IngredientReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientReportRepository extends JpaRepository<IngredientReport, Integer> {
}
