package com.stock.sweet.sweetstockapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.LoginResponse;
import com.stock.sweet.sweetstockapi.mapper.CompanyMapper;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.stock.sweet.sweetstockapi.security.JwtAuthenticationFilter.TOKEN_EXPIRATION;
import static com.stock.sweet.sweetstockapi.security.JwtAuthenticationFilter.TOKEN_PASSWORD;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createCompany(@RequestBody CompanyRequest body) {
        var company = companyService.createCompany(companyMapper.convertRequestToModel(body));
        var user = userService.createUser(companyMapper.convertRequestToUserModel(body, company.getId()));

        String token = JWT.create()
                .withSubject(user.getEmail())
                .withPayload(Map.of(
                        "role", user.getLevelAccess(),
                        "companyId", company.getUuid()
                ))
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));


        return ResponseEntity.status(201).body(LoginResponse.builder()
                .token(token)
                .username(company.getCeo())
                .build()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.convertModelListToResponseList(
                companyService.getAllCompanys()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompanyByUuid(@PathVariable String uuid) throws Exception {
        return companyMapper.convertModelToResponse(
                companyService.findCompanyByUuid(uuid)
        );
    }
}
