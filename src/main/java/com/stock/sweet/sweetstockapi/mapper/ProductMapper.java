package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private IngredientMapper ingredientMapper;

    public Product convertRequestToModel(ProductRequest productRequest) {
        Set<Confection> confections = new HashSet<>();

        return new Product(
                null,
                UUID.randomUUID().toString(),
                productRequest.getName(),
                productRequest.getUnitMeasurement().toString(),
                productRequest.getSaleValue(),
                productRequest.getTotal(),
                productRequest.getExpirationDate(),
                LocalDate.now(),
                LocalDate.now(),
                productRequest.getIsRefrigerated(),
                false,
                null,
                productRequest.getPicture(),
                confections,
                productRequest.getCompany(),
                productRequest.getCategory()
        );
    }

    public ProductResponse convertModelToResponse(Product product) {
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getSaleValue(),
                product.getExpirationDate(),
                product.getDateInsert(),
                product.getDateUpdate(),
                product.getIsRefigerated(),
                product.getSold(),
                product.getTotal(),
                product.getUnitMeasurement(),
                product.getCategory(),
                product.getPicture()
                );
    }

    public List<ProductResponse> convertModelListToResponseList(List<Product> products) {
        return products.stream().map(p -> {
            return new ProductResponse(
                    p.getUuid(),
                    p.getName(),
                    p.getSaleValue(),
                    p.getExpirationDate(),
                    p.getDateInsert(),
                    p.getDateUpdate(),
                    p.getIsRefigerated(),
                    p.getSold(),
                    p.getTotal(),
                    p.getUnitMeasurement(),
                    p.getCategory(),
                    p.getPicture()
            );
        }).collect(Collectors.toList());
    }
}