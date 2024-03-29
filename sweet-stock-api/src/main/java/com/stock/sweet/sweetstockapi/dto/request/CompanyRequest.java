package com.stock.sweet.sweetstockapi.dto.request;

import com.stock.sweet.sweetstockapi.controller.enums.PeopleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String fantasyName;
    private String telephoneNumber;
    private PeopleType peopleType;
    private String ceo;
    private String cpf;
    private String cnpj;
    private AddressRequest addressRequest;
    private String email;
    private String password;
    private String openHour;
    private String closeHour;
    private Integer daysOpen;
}
