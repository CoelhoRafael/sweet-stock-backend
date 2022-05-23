package com.stock.sweet.sweetstockapi.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.sweet.sweetstockapi.config.security.data.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(HEADER_ATTRIBUTE);

        if (attribute == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!attribute.startsWith(ATTRIBUTE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = attribute.replace(ATTRIBUTE_PREFIX, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) throws JsonProcessingException {
        String username = getSubjectFromToken(token);

        ArrayList<Role> authorities = getRolesFromToken(token);

        if (username == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    private ArrayList<Role> getRolesFromToken(String token) throws JsonProcessingException {
        var payloadFromToken = new String(Base64.getDecoder().decode(
                JWT.require(Algorithm.HMAC512(JwtAuthenticationFilter.TOKEN_PASSWORD))
                        .build()
                        .verify(token)
                        .getPayload()));

        ObjectMapper objectMapper = new ObjectMapper();

        var mappedPayload = objectMapper.readValue(payloadFromToken, Map.class);

        var authorities = new ArrayList<Role>();
        authorities.add(new Role(mappedPayload.get("role").toString()));
        return authorities;
    }

    private String getSubjectFromToken(String token) {
        String username = JWT.require(Algorithm.HMAC512(JwtAuthenticationFilter.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();
        return username;
    }
}
