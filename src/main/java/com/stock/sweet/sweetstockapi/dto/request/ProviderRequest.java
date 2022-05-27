package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderRequest {
    private String name;
    private String cnpj;
    private String email;
    private String telephone;
    private Integer averageTimeForDeliveryInDays;
}
