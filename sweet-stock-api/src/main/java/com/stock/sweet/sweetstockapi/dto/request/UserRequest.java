package com.stock.sweet.sweetstockapi.dto.request;

import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class UserRequest {
    private String name;

    private String email;

    private String password;

    private String telephoneNumber;

    private LevelAccess levelAccess;
}
