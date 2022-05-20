package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.IngredientReportRequest;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.IngredientReport;
import com.stock.sweet.sweetstockapi.repository.IngredientReportRepository;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngredientReportService {

    @Autowired
    private IngredientReportRepository ingredientReportRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientReport createIngredientReport(IngredientReport ingredientReport) {
        return ingredientReportRepository.save(ingredientReport);
    }

    public String createIngredientsReportForDays(IngredientReportRequest body) {
        List<Ingredient> ingredientsExpired = ingredientRepository.findExpiredIngredientsByDate(LocalDate.now().plusDays(body.getQuantityDaysForGenerateReport()));

        String formatedColumns = "Id;" +
                "Nome;" +
                "Quantidade;" +
                "Validade;" +
                "Temperatura de armazenamento;" +
                "Refrigeração;" +
                "Valor de compra;" +
                "Cod. Fornecedor;" +
                "Quantidade utilizada;" +
                "Data registro;" +
                "Número do lote\n";

        String stringFormatIngredient = "%s;%.2f;%s;%.2f;%s;%.2f;%d;%.2f;%s;%d\r\n";

        String ingredientReport = "";
        ingredientReport += formatedColumns;


        for (Ingredient ingredientExpired : ingredientsExpired) {
            ingredientReport += String.format(stringFormatIngredient,
                    ingredientExpired.getName(),
                    0.0,
//                    ingredientExpired.getQuantity(),
                    ingredientExpired.getExpirationDate(),
                    ingredientExpired.getStorageTemperature(),
                    ingredientExpired.getIsRefigerated() ? "Sim" : "Não",
                    ingredientExpired.getBuyValue(),
                    ingredientExpired.getProvideCode(),
                    ingredientExpired.getQuantityUsed(),
                    ingredientExpired.getDateInsert(),
                    ingredientExpired.getNumberLot()
            );
        }
        return ingredientReport;
    }
}
