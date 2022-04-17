package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.CategoryRequest;
import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.model.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public Category convertRequestToModel(CategoryRequest categoryRequest) {
        return new Category(
                null,
                UUID.randomUUID().toString(),
                categoryRequest.getName(),
                categoryRequest.getStorageTemperature(),
                categoryRequest.getIsRefrigerated(),
                categoryRequest.getUnitMeasurement(),
                LocalDateTime.now(),
                null
        );
    }

    public CategoryResponse convertModelToResponse(Category category) {
        return new CategoryResponse(
                category.getUuid(),
                category.getName(),
                category.getStorageTemperature(),
                category.getIsRefrigerated(),
                category.getUnitMeasurement(),
                category.getDateInsert(),
                category.getDateUpdate()
        );
    }

    public List<CategoryResponse> convertModelListToResponseList(List<Category> categories) {
        return categories.stream().map(c -> {
            return new CategoryResponse(
                    c.getUuid(),
                    c.getName(),
                    c.getStorageTemperature(),
                    c.getIsRefrigerated(),
                    c.getUnitMeasurement(),
                    c.getDateInsert(),
                    c.getDateUpdate()
            );
        }).collect(Collectors.toList());
    }
}