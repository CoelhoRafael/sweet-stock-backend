package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponse {
    private String uuid;
    private String name;
    private String fantasyName;
    private String ceo;
    private String cpf;
    private String cnpj;
    private String email;
    private String telephoneNumber;
}
