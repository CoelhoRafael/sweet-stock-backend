package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.model.enums.CategoryEnum;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories() {
        return Arrays.stream(CategoryEnum.values()).map(categoryEnum -> {
            return new CategoryResponse(
                    categoryEnum.toString(),
                    categoryEnum.name()
            );
        }).collect(Collectors.toList());
    }
}