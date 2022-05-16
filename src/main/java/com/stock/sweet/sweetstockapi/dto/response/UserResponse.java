package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {
    private String uuid;
    private String name;
    private String email;
    private String telephoneNumber;
    private String levelAccess;
    private boolean aproved;
    private String picture;
}
