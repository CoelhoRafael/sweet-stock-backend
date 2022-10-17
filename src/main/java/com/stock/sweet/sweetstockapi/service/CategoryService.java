package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public ResponseEntity<List<CategoryResponse>> addCategories(List<Category> categoryList) {

        List<Category> saved = categoryRepository.saveAll(categoryList);

        if (saved.size() == 0) {
            return ResponseEntity.status(400).build();
        }

        List<CategoryResponse> listSaved = new ArrayList<>();

        saved.forEach(category -> {
            listSaved.add(new CategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getPicture()
            ));
        });

        if (listSaved.size() == 0) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(201).body(listSaved);
    }

    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CategoryResponse> listfounds = new ArrayList<>();

        categories.forEach(category -> {
            listfounds.add(new CategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getPicture()
            ));
        });

        return ResponseEntity.status(200).body(listfounds);
    }

    public ResponseEntity deleteCategories(List<String> categoriesList) {
        categoriesList.forEach(c ->{
            var exist = categoryRepository.findById(c);
            if(!exist.isEmpty()){
                categoryRepository.deleteById(c);
            }
        });
        return ResponseEntity.noContent().build();
    }
}
