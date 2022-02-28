package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.ConfectionRequest;
import com.stock.sweet.sweetstockapi.dto.request.IngredientRequest;
import com.stock.sweet.sweetstockapi.dto.response.ConfectionResponse;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.mapper.ConfectionMapper;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.service.ConfectionService;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/confections")
public class ConfectionController {

    @Autowired
    private ConfectionMapper confectionMapper;

    @Autowired
    private ConfectionService confectionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConfectionResponse createConfection(@RequestBody ConfectionRequest body){
        return confectionMapper.convertModelToResponse(
                confectionService.createConfection(confectionMapper.convertRequestToModel(body))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConfectionResponse> getAllProviders(){
        return confectionMapper.convertModelListToResponseList(
                confectionService.getAllConfections()
        );
    }
}