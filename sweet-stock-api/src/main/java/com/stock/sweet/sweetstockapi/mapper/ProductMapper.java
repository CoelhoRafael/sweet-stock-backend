package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.stereotype.Component;

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
                productRequest.getSaleValue(),
                productRequest.getExpirationDate(),
                productRequest.getDateInsert(),
                productRequest.getDateUpdate(),
                productRequest.getCategory(),
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
                product.getDateUpdate(),
                product.getCategory()
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
                    p.getCategory()
            );
        }).collect(Collectors.toList());
    }
}