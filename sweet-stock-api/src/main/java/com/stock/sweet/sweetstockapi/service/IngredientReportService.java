package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.IngredientReport;
import com.stock.sweet.sweetstockapi.repository.IngredientReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientReportService {

    @Autowired
    private IngredientReportRepository ingredientReportRepository;

    public IngredientReport createIngredientReport(IngredientReport ingredientReport) {
        return ingredientReportRepository.save(ingredientReport);
    }
}
