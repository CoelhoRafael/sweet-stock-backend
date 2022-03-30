package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRequest {
    private String uuid;
    private String name;
    private String fantasyName;
    private String ceo;
    private String cpf;
    private String cnpj;
    private String email;
    private String telephoneNumber;
}
