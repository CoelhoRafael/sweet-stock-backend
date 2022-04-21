package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
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

        String report = FileCSV.chaseFileCSV(ingredientsExpiredObject, nameReport);

        return report;
    }

    public List<Ingredient> getAllIngredientsByCompanyUuid(String uuid) {
        List<Ingredient> ingredients = ingredientRepository.findAllByUuidCompany(uuid);
        return ingredients;
    }

    public List<Ingredient> getAllIngredientsNearExpire(String uuid) {
        List<Ingredient> ingredients = ingredientRepository.findAllByUuidCompany(uuid);

        ingredients = ingredients.stream().filter(ingredient -> ingredient.getExpirationDate().isAfter(LocalDate.now().minusDays(15)
        ) || ingredient.getExpirationDate().isEqual(LocalDate.now().minusDays(15))).collect(Collectors.toList());
        return ingredients;
    }

    public List<Ingredient> getExpiredIngredients(String uuid) {
        return ingredientRepository.findAllByUuidCompany(uuid)
                .stream()
                .filter(ingredient -> ingredient.getExpirationDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
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
}
