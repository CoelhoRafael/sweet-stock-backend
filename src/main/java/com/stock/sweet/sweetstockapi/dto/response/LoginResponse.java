package com.stock.sweet.sweetstockapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private String token;
    private String username;
    private String levelAccess;
    private String picture;
}
