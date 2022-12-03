package com.stock.sweet.sweetstockapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.LoginResponse;
import com.stock.sweet.sweetstockapi.mapper.CompanyMapper;
import com.stock.sweet.sweetstockapi.model.ListCategories;
import com.stock.sweet.sweetstockapi.repository.ListCategoriesRepository;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.EmailService;
import com.stock.sweet.sweetstockapi.service.UserService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.stock.sweet.sweetstockapi.config.security.JwtAuthenticationFilter.TOKEN_EXPIRATION;
import static com.stock.sweet.sweetstockapi.config.security.JwtAuthenticationFilter.TOKEN_PASSWORD;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
@SecurityRequirement(name = "bearerAuth")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ListCategoriesRepository listCategoriesRepository;

    @Autowired
    private HeadersUtils headersUtils;

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

        body.getCategories().forEach(category -> {
            listCategoriesRepository.save(new ListCategories(
                    null,
                    company.getUuid(),
                    category
            ));
        });

        return ResponseEntity.status(201).body(LoginResponse.builder()
                .token(token)
                .username(company.getCeo())
                .levelAccess(user.getLevelAccess())
                .picture(company.getPicture())
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


    @GetMapping("/get-name-company/{uuidProduct}")
    public ResponseEntity<String> getNameCompany(@PathVariable String uuidProduct) {
        return companyService.getNameCompany(uuidProduct);
    }

    @GetMapping("/get-name-company-jwt")
    public ResponseEntity<String> getNameCompanyJwt(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        String uuidCompany = headersUtils.getCompanyIdFromToken(headers);
        return companyService.getNameCompanyJwt(uuidCompany);
    }

    @GetMapping("/get-companies-by-category/{nameCategory}")
    public ResponseEntity<List<CompanyResponse>> getCompaniesByCategory(@PathVariable String nameCategory) {
        return ResponseEntity.ok().body(
                companyMapper.convertModelListToResponseList(companyService.getCompaniesByCategory(nameCategory))
        );
    }
}
