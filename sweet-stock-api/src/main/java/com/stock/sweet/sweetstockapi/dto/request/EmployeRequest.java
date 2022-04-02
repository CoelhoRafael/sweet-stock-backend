package com.stock.sweet.sweetstockapi.dto.request;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class EmployeRequest {
    private String name;
    private String companyCode;
    private String telephoneNumber;
    private String email;
    private String password;
}
