package com.stock.sweet.sweetstockapi.dto.response.app.home;

import com.stock.sweet.sweetstockapi.dto.response.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyHomeResponse {
    private String uuid;
    private String name;
    private String fantasyName;
    private String email;
    private String telephoneNumber;
    private String picture;
    private Double rating;
    private AddressResponse address;
}
