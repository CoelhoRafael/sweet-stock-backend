package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderResponse {
    private Integer id;
    private String uuid;
    private String name;
    private String cnpj;
    private String email;
    private String telephone;
    private Integer averageTimeForDeliveryInDays;
}
