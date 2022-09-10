package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private IngredientMapper ingredientMapper;

    public Product convertRequestToModel(ProductRequest productRequest) {
        List<Confection> confections = new ArrayList<>();

        return new Product(
                null,
                UUID.randomUUID().toString(),
                productRequest.getName(),
                productRequest.getUnitMeasurement(),
                productRequest.getSaleValue(),
                productRequest.getTotal(),
                productRequest.getExpirationDate(),
                LocalDate.now(),
                LocalDate.now(),
                productRequest.getCategory().toString(),
                productRequest.getIsRefrigerated(),
                false,
                productRequest.getPicture(),
                confections
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