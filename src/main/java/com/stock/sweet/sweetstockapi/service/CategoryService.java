package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.CategoryRequest;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("Corpo vazio");
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        if (categoryRepository.findAll().isEmpty())throw new IllegalArgumentException();
        return categoryRepository.findAll();
    }

    public Category findCategoryByUuid(String uuid) throws Exception {
        return categoryRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }

    public Category updateCategory(String uuid, CategoryRequest categoryRequest) throws Exception {
        Category categoryToUpdate = findCategoryByUuid(uuid);

        if (categoryToUpdate == null) {
            return null;
        }

        categoryToUpdate.setName(categoryRequest.getName());
        categoryToUpdate.setStorageTemperature(categoryRequest.getStorageTemperature());
        categoryToUpdate.setIsRefrigerated(categoryRequest.getIsRefrigerated());
        categoryToUpdate.setUnitMeasurement(categoryRequest.getUnitMeasurement());
        categoryToUpdate.setDateUpdate(LocalDateTime.now());

        categoryRepository.save(categoryToUpdate);

        return categoryToUpdate;
    }

    public Category deleteCategory(String uuid) throws Exception {
        Category categoryToDelete = findCategoryByUuid(uuid);

        if (categoryToDelete == null) {
            return null;
        }

        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }
}
