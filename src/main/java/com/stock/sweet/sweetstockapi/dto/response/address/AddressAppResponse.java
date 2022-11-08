package com.stock.sweet.sweetstockapi.dto.response.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressAppResponse {
    private String uuid;
    private String street;
    private String neighborhood;
    private String number;
}