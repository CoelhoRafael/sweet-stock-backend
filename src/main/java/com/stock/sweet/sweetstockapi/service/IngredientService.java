package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.IngredientToUpdateRequest;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.utils.ExportTXT;
import com.stock.sweet.sweetstockapi.utils.FileCSV;
import com.stock.sweet.sweetstockapi.utils.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;


    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public String ExpiredIngredientsCSV() {
        String nameReport = "relatorio-ingredientes-vencidos-" + LocalDate.now();
        List<Ingredient> ingredientsExpired = ingredientRepository.findIngredientExpired(LocalDate.now());


        for (int i = 0; i < ingredientsExpired.size(); i++) {
            ingredientRepository.updateForTrueExpiratedIngredient(ingredientsExpired.get(i).getUuid());
        }

        ListObject<Ingredient> ingredientsExpiredObject = new ListObject<>(ingredientsExpired.size());
        ingredientsExpired.forEach(ingredientsExpiredObject::add);

        return FileCSV.chaseFileCSV(ingredientsExpiredObject, nameReport);
    }

    public String IngredientsTXT(String uuid) {
        String NAME_ARQUIVO = "ingredients" + LocalDate.now() + ".txt";

        List<Ingredient> ingredientsExpired = ingredientRepository.findAllByUuidCompany(uuid);
        System.out.println(ingredientsExpired);
        return ExportTXT.gravaArquivoTxt(ingredientsExpired, NAME_ARQUIVO);
    }

    public List<Ingredient> getAllIngredientsByCompanyUuid(String uuid) {
        List<Ingredient> ingredients = ingredientRepository.findAllByUuidCompany(uuid);
        return ingredients;
    }

    public List<Ingredient> getAllIngredientsNearExpire(String uuid) {
        return ingredientRepository.getAllIngredientsNearExpire(
                uuid,
                LocalDate.now().plusDays(15)
        );
    }

    public List<Ingredient> getExpiredIngredients(String uuid) {
        List<Ingredient> expiredIngredients = ingredientRepository.expiredIngredients(uuid, LocalDate.now());

        return expiredIngredients;
    }

    public List<Ingredient> getAllIngredients(String uuid) {
        return ingredientRepository.findAllByUuidCompany(uuid);
    }

    public List<Ingredient> getIngredientsCurrentMonth(String uuid) {
        return ingredientRepository.findAllByUuidCompany(uuid)
                .stream()
                .filter(ingredient -> ingredient.getDateInsert().withDayOfMonth(1).equals(LocalDate.now().withDayOfMonth(1)))
                .collect(Collectors.toList());
    }

    public Ingredient updateIngredient(String uuid, IngredientToUpdateRequest ingredientRequest) throws Exception {

        Ingredient ingredientToUpdate = ingredientRepository.findIngredientByUuid(uuid);

        if (ingredientToUpdate == null) {
            return null;
        }

        ingredientToUpdate.setName(ingredientRequest.getName());
        ingredientToUpdate.setUnitMeasurement(ingredientRequest.getUnitMeasurement());
        ingredientToUpdate.setQuantityPerUnit(ingredientRequest.getQuantityPerUnit());
        ingredientToUpdate.setTotal(ingredientRequest.getTotal());
        ingredientToUpdate.setNumberUnits(ingredientRequest.getNumberUnits());
        ingredientToUpdate.setQuantityUsed(ingredientRequest.getQuantityUsed());
        ingredientToUpdate.setExpirationDate(ingredientRequest.getExpirationDate());
        ingredientToUpdate.setStorageTemperature(ingredientRequest.getStorageTemperature());
        ingredientToUpdate.setIsRefigerated(ingredientRequest.getIsRefigerated());
        ingredientToUpdate.setBuyValue(ingredientRequest.getBuyValue());
        ingredientToUpdate.setProvideCode(ingredientRequest.getProvideCode());

        ingredientRepository.save(ingredientToUpdate);

        return ingredientToUpdate;
    }

    public Ingredient deleteIngredient(String uuid) throws Exception {
        Ingredient ingredientToDelete = (Ingredient) ingredientRepository.findIngredientByUuid(uuid);

        if (ingredientToDelete == null) {
            return null;
        }

        ingredientRepository.delete(ingredientToDelete);
        return ingredientToDelete;
    }

    public Ingredient getIngredientByUuid(String uuid) {
        Ingredient ingredient = ingredientRepository.findIngredientByUuid(uuid);
        if (ingredient != null){
            return ingredient;
        }
        return null;
    }

}
