package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.utils.ArqCSV;
import com.stock.sweet.sweetstockapi.utils.ListaObj;
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

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public ListaObj<Ingredient> downloadCSVExpiredIngredients() {
        var expiredIngredients = ingredientRepository.findAll()
                .stream()
                .filter(a -> LocalDate.now().isAfter(a.getExpirationDate()))
                .collect(Collectors.toList());

        ListaObj<Ingredient> listObjIngredients = new ListaObj<>(expiredIngredients.size());
        expiredIngredients.forEach(listObjIngredients::adiciona);
        ArqCSV.gravaArquivoCsv( listObjIngredients,"Ingrediente-vencidos");
        return listObjIngredients;
    }



}
