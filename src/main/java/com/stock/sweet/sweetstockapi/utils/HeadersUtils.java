package com.stock.sweet.sweetstockapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.sweet.sweetstockapi.security.JwtAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

import static com.stock.sweet.sweetstockapi.security.JwtValidationFilter.ATTRIBUTE_PREFIX;

@Component
public class HeadersUtils {

    public String getCompanyIdFromToken(String token) throws JsonProcessingException {
        token = token.replace(ATTRIBUTE_PREFIX, "");

        var payloadFromToken = new String(Base64.getDecoder().decode(
                JWT.require(Algorithm.HMAC512(JwtAuthenticationFilter.TOKEN_PASSWORD))
                        .build()
                        .verify(token)
                        .getPayload()));

        ObjectMapper objectMapper = new ObjectMapper();

        var mappedPayload = objectMapper.readValue(payloadFromToken, Map.class);

        return mappedPayload.get("companyId").toString();

    }
}
