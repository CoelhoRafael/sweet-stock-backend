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
        String nameRelatory = "relatorio-ingredientes-vencidos-" + LocalDate.now();
        List<Ingredient> ingredientsExpired = ingredientRepository.findIngredientExpired(LocalDate.now());

        ListObject<Ingredient> ingredientsExpiredObject = new ListObject<>(ingredientsExpired.size());
        ingredientsExpired.forEach(ingredientsExpiredObject::add);

        String relatory = FileCSV.chaseFileCSV(ingredientsExpiredObject, nameRelatory);

        return relatory;
    }


}
