package com.stock.sweet.sweetstockapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.sweet.sweetstockapi.dto.request.UserLogin;
import com.stock.sweet.sweetstockapi.security.data.UserDetailsData;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600_000;
    public static final String TOKEN_PASSWORD = "506501fb-82f6-4795-ae28-d2805ce94c20";

    private final AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLogin user = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(), user.getPassword(), new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar o usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsData userDetailsData = (UserDetailsData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetailsData.getUsername())
                .withPayload(Map.of("role", userDetailsData.getAuthorities().stream().findFirst().get().getAuthority()))
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
