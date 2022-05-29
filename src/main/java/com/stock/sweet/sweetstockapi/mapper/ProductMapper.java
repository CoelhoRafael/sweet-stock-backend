package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestDashboard;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product convertRequestToModel(ProductRequest productRequest) {
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
                productRequest.getProductedBy(),
                productRequest.getCategory(),
                productRequest.getIsRefigerated(),
                productRequest.getPicture(),
                null
        );
    }
    public Product convertRequestToModel(ProductRequestDashboard productRequest) {
        return new Product(
                null,
                UUID.randomUUID().toString(),
                productRequest.getName(),
                productRequest.getUnitMeasurement(),
                productRequest.getSaleValue(),
                productRequest.getTotal(),
                null,
                LocalDate.now(),
                LocalDate.now(),
                productRequest.getProductedBy(),
                null,
                productRequest.getIsRefigerated(),
                productRequest.getPicture(),
                null
        );
    }

    public ProductResponse convertModelToResponse(Product product) {
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getSaleValue(),
                product.getExpirationDate(),
                product.getDateInsert(),
                product.getProductedBy(),
                product.getDateUpdate(),
                product.getIsRefigerated(),
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
                    p.getProductedBy(),
                    p.getDateUpdate(),
                    p.getIsRefigerated(),
                    p.getTotal(),
                    p.getUnitMeasurement(),
                    p.getCategory(),
                    p.getPicture()
            );
        }).collect(Collectors.toList());
    }
}