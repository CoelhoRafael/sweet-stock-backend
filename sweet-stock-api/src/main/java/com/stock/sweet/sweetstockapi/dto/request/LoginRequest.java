package com.stock.sweet.sweetstockapi.dto.request;

import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
