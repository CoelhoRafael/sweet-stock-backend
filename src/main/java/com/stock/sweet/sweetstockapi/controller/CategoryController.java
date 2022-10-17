package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.model.enums.CategoryEnum;
import com.stock.sweet.sweetstockapi.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<List<CategoryResponse>> addCategories(
            @RequestBody List<Category> categoryList
    ){
        return categoryService.addCategories(categoryList);
    }

    @DeleteMapping
    public ResponseEntity deleteCategories(
            @RequestBody List<String> categoriesList
    ){
        return categoryService.deleteCategories(categoriesList);
    }
}