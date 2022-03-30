package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private String telephoneNumber;
    private String levelAccess;
}
