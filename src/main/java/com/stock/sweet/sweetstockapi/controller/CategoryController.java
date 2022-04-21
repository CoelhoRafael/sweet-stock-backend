package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.CategoryRequest;
import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.mapper.CategoryMapper;
import com.stock.sweet.sweetstockapi.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestBody CategoryRequest body) {
        return categoryMapper.convertModelToResponse(
                categoryService.createCategory(categoryMapper.convertRequestToModel(body))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.convertModelListToResponseList(
                categoryService.getAllCategories()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryByUuid(@PathVariable String uuid) throws Exception {
        return categoryMapper.convertModelToResponse(
                categoryService.findCategoryByUuid(uuid)
        );
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse updateCategory(@PathVariable String uuid, @RequestBody CategoryRequest body) throws Exception {
        return categoryMapper.convertModelToResponse(
                categoryService.updateCategory(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoryResponse deleteCategory(@PathVariable String uuid) throws Exception {
        return categoryMapper.convertModelToResponse(
                categoryService.deleteCategory(uuid)
        );
    }

}