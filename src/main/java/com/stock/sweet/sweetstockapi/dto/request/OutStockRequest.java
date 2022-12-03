package com.stock.sweet.sweetstockapi.dto.request;


import com.stock.sweet.sweetstockapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OutStockRequest {
    private LocalDate date;
    private String idCompany;
    private Product product;
}
