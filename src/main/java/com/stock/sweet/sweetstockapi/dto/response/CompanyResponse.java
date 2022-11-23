package com.stock.sweet.sweetstockapi.dto.response;

import com.stock.sweet.sweetstockapi.dto.response.address.AddressAppResponse;
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
    private String picture;
    private AddressAppResponse address;
    private boolean activated;
    private Boolean isOpen;
}
