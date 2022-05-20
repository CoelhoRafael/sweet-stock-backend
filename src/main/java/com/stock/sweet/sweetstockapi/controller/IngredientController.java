package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.request.IngredientToUpdateRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class IngredientController {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createIngredient(
            @RequestBody IngredientRequest body,
            @RequestHeader HttpHeaders headers
    ) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        try {
            ingredientService.createIngredient(ingredientMapper.convertRequestToModel(body, uuidCompany));
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResponse> getAllIngredients(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return ingredientMapper.convertModelListToResponseList(
                ingredientService.getAllIngredients(uuidCompany)
        );
    }



    @GetMapping("/expired")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllExpiredIngredients() {

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-ingredientes-vencidos.csv\"")
                .body(ingredientService.ExpiredIngredientsCSV());

    }

    @GetMapping("/arq-txt")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllIngredientsTXT(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return ResponseEntity
                .status(200)
                .header("content-type", "text/txt")
                .header("content-disposition", "filename=\"ingredients.txt\"")
                .body(ingredientService.IngredientsTXT(uuidCompany));

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientResponse updateIngredients(@PathVariable String uuid, @RequestBody IngredientToUpdateRequest body)throws Exception{
        return ingredientMapper.convertModelToResponse(
                ingredientService.updateIngredient(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public IngredientResponse deleteIngredients(@PathVariable String uuid)throws Exception{
           return ingredientMapper.convertModelToResponse(
                  ingredientService.deleteIngredient(uuid)
           );

        }

}
