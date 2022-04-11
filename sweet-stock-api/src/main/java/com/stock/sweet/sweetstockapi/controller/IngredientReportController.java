package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.IngredientReportRequest;
import com.stock.sweet.sweetstockapi.mapper.IngredientReportMapper;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.service.IngredientReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ingredients-reports")
public class IngredientReportController {

    @Autowired
    private IngredientReportMapper ingredientReportMapper;

    @Autowired
    private IngredientReportService ingredientReportService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createIngredientReport(@RequestBody IngredientReportRequest body) {
        ingredientReportMapper.convertModelToResponse(
                ingredientReportService.createIngredientReport(ingredientReportMapper.convertRequestToModel(body)));

        List<Ingredient> ingredientsExpired = ingredientRepository.findExpiredIngredientsForDays(LocalDate.now().plusDays(body.getQuantityDaysForGenerateReport()));

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
                    ingredientExpired.getQuantity(),
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
        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-pets.csv\"")
                .body(ingredientReport);
    }


}
