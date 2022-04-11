package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.utils.FileCSV;
import com.stock.sweet.sweetstockapi.utils.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;


    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
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


}
