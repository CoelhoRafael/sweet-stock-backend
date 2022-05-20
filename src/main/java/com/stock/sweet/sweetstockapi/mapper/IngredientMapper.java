package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    public Ingredient convertRequestToModel(IngredientRequest ingredientRequest, String uuidCompany) {
        return new Ingredient(
                null,
                UUID.randomUUID().toString(),
                ingredientRequest.getName(),
                ingredientRequest.getUnitMeasurement(),
                ingredientRequest.getNumberUnits(),
                ingredientRequest.getQuantityPerUnit(),
                ingredientRequest.getExpirationDate(),
                ingredientRequest.getStorageTemperature(),
                ingredientRequest.getIsRefigerated(),
                ingredientRequest.getBuyValue(),
                ingredientRequest.getProvideCode(),
                ingredientRequest.getQuantityUsed(),
                LocalDate.now(),
                LocalDate.now(),
                ingredientRequest.getNumberLot(),
                false,
                true,
                uuidCompany,
                ingredientRequest.getTotal(),
                null
        );
    }

    public IngredientResponse convertModelToResponse(Ingredient ingredient) {
        return new IngredientResponse(
                ingredient.getUuid(),
                ingredient.getName(),
                ingredient.getUnitMeasurement(),
                ingredient.getNumberUnits(),
                ingredient.getQuantityPerUnit(),
                ingredient.getExpirationDate(),
                ingredient.getStorageTemperature(),
                ingredient.getIsRefigerated(),
                ingredient.getBuyValue(),
                ingredient.getProvideCode(),
                ingredient.getQuantityUsed(),
                ingredient.getDateInsert(),
                ingredient.getDateUpdate(),
                ingredient.getNumberLot(),
                ingredient.getExpirated(),
                ingredient.getViewInReports(),
                ingredient.getUuidCompany(),
                ingredient.getTotal()
        );
    }

    public List<IngredientResponse> convertModelListToResponseList(List<Ingredient> ingredients) {
        return ingredients.stream().map(i -> {
            return new IngredientResponse(
                    i.getUuid(),
                    i.getName(),
                    i.getUnitMeasurement(),
                    i.getNumberUnits(),
                    i.getQuantityPerUnit(),
                    i.getExpirationDate(),
                    i.getStorageTemperature(),
                    i.getIsRefigerated(),
                    i.getBuyValue(),
                    i.getProvideCode(),
                    i.getQuantityUsed(),
                    i.getDateInsert(),
                    i.getDateUpdate(),
                    i.getNumberLot(),
                    i.getExpirated(),
                    i.getViewInReports(),
                    i.getUuidCompany(),
                    i.getTotal()
            );
        }).collect(Collectors.toList());
    }
}
