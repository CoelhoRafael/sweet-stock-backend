package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.request.IngredientToUpdateRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.stock.sweet.sweetstockapi.utils.ExportTXT;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.stock.sweet.sweetstockapi.utils.ExportTXT.gravaArquivoTxt;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class IngredientController {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private IngredientRepository ingredientRepository;

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
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers);
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
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers);
        return ingredientMapper.convertModelListToResponseList(
                ingredientService.getAllIngredients(uuidCompany)
        );
    }


    @GetMapping("/expired-csv")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllExpiredIngredients(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers);
        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-ingredientes-vencidos.csv\"")
                .body(ingredientService.ExpiredIngredientsCSV(uuidCompany));

    }

    @GetMapping("/expired")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllExpiredIngredientsList(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers);
        return ResponseEntity
                .status(200)
                .body(ingredientService.getExpiredIngredients(uuidCompany));

    }

    @GetMapping("/arq-txt")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllIngredientsTXT(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers);
        String NAME_ARQUIVO = "ingredients" + LocalDate.now() + ".txt";

        List<Ingredient> ingredientsExpired = ingredientRepository.findIngredientExpired(LocalDate.now(),uuidCompany);

       return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"ingredients.txt\"")
                .body(ExportTXT.gravaArquivoTxt(ingredientsExpired, NAME_ARQUIVO));

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientResponse updateIngredients(@PathVariable String uuid, @RequestBody IngredientToUpdateRequest body) throws Exception {
        return ingredientMapper.convertModelToResponse(
            ingredientService.updateIngredient(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public IngredientResponse deleteIngredients(@PathVariable String uuid) throws Exception {
        return ingredientMapper.convertModelToResponse(
                ingredientService.deleteIngredient(uuid)
        );
    }

    @GetMapping("/ingredient-by-uuid/{uuid}")
    public ResponseEntity<IngredientResponse> ingredientByUuid(@PathVariable String uuid) {
        Ingredient ingredient = ingredientService.getIngredientByUuid(uuid);
        if (ingredient != null) {
            return ResponseEntity.status(200).body(ingredientMapper.convertModelToResponse(
                    ingredientService.getIngredientByUuid(uuid)));
        }
        return ResponseEntity.status(404).build();
    }
}
