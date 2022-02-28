package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequest {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String neighborhood;
}
