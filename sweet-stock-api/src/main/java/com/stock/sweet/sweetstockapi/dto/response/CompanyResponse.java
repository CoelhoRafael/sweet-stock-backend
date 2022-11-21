package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

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
    private String openHour;
    private String closeHour;
    private Integer daysOpen;
}
