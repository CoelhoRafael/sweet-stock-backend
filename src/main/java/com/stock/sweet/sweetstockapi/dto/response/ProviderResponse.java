package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderResponse {
    private String uuid;
    private String name;
    private String cnpj;
    private Integer averageTimeForDeliveryInDays;
}
