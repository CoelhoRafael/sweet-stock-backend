package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientResponse createIngredient(@RequestBody IngredientRequest body) {
        return ingredientMapper.convertModelToResponse(
                ingredientService.createIngredient(ingredientMapper.convertRequestToModel(body))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResponse> getAllIngredients() {
        return ingredientMapper.convertModelListToResponseList(
                ingredientService.getAllIngredients()
        );
    }

    @GetMapping("/expired")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResponse> getAllExpiredIngredients() {
        return null;
    }
}
