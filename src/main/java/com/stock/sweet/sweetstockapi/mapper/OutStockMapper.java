package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.OutStockRequest;
import com.stock.sweet.sweetstockapi.model.OutStock;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OutStockMapper {

    public OutStock convertRequestToModel(OutStockRequest outStockRequest){
        return OutStock.builder()
                .uuid(UUID.randomUUID().toString())
                .idCompany(outStockRequest.getIdCompany())
                .date(outStockRequest.getDate())
                .product(outStockRequest.getProduct())
                .build();

    }
}
