package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.Provider;
import com.stock.sweet.sweetstockapi.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    @Autowired
    private ProviderService providerService;

    public Ingredient convertRequestToModel(IngredientRequest ingredientRequest, String uuidCompany) {
        return new Ingredient(
                null,
                UUID.randomUUID().toString(),
                ingredientRequest.getName(),
                ingredientRequest.getUnitMeasurement() == null ? UnitMeasurement.UNIDADE : ingredientRequest.getUnitMeasurement(),
                ingredientRequest.getNumberUnits(),
                ingredientRequest.getQuantityPerUnit(),
                ingredientRequest.getExpirationDate(),
                ingredientRequest.getStorageTemperature(),
                ingredientRequest.getIsRefigerated() != null,
                ingredientRequest.getBuyValue(),
                ingredientRequest.getProvideCode(),
                0,
                LocalDate.now(),
                LocalDate.now(),
                ingredientRequest.getNumberLot(),
                false,
                true,
                uuidCompany,
                (ingredientRequest.getQuantityPerUnit() * ingredientRequest.getNumberUnits()),
                ingredientRequest.getPicture(),
                ingredientRequest.getBrand(),
                ingredientRequest.getPurchaseDate(),
                null
        );
    }

    public IngredientResponse convertModelToResponse(Ingredient ingredient) {
        String providerName = "";

        if(ingredient.getProvideCode() != null){
            Provider provider = providerService.getAllProvidersById(ingredient.getProvideCode());
            providerName = provider.getName();
        }

        return new IngredientResponse(
                ingredient.getUuid(),
                ingredient.getNumberLot(),
                ingredient.getName(),
                ingredient.getUnitMeasurement(),
                ingredient.getNumberUnits(),
                ingredient.getQuantityPerUnit(),
                ingredient.getExpirationDate(),
                ingredient.getStorageTemperature(),
                ingredient.getIsRefigerated(),
                ingredient.getBuyValue(),
                providerName,
                ingredient.getQuantityUsed(),
                ingredient.getDateInsert(),
                ingredient.getDateUpdate(),
                ingredient.getExpirated(),
                ingredient.getViewInReports(),
                ingredient.getUuidCompany(),
                ingredient.getTotal(),
                ingredient.getBrand(),
                ingredient.getPicture()
        );
    }

    public List<IngredientResponse> convertModelListToResponseList(List<Ingredient> ingredients) {
        AtomicReference<String> providerName = new AtomicReference<>("");

        return ingredients.stream().map(i -> {
            if(i.getProvideCode() != null){
                Provider provider = providerService.getAllProvidersById(i.getProvideCode());
                providerName.set(provider.getName());
            }
            return new IngredientResponse(
                    i.getUuid(),
                    i.getNumberLot(),
                    i.getName(),
                    i.getUnitMeasurement(),
                    i.getNumberUnits(),
                    i.getQuantityPerUnit(),
                    i.getExpirationDate(),
                    i.getStorageTemperature(),
                    i.getIsRefigerated(),
                    i.getBuyValue(),
                    providerName.get(),
                    i.getQuantityUsed(),
                    i.getDateInsert(),
                    i.getDateUpdate(),
                    i.getExpirated(),
                    i.getViewInReports(),
                    i.getUuidCompany(),
                    i.getTotal(),
                    i.getBrand(),
                    i.getPicture()
            );
        }).collect(Collectors.toList());
    }
}
