package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.ConfectionRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.ConfectionResponse;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.mapper.ConfectionMapper;
import com.stock.sweet.sweetstockapi.mapper.ProductMapper;
import com.stock.sweet.sweetstockapi.service.ConfectionService;
import com.stock.sweet.sweetstockapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest body){
        return productMapper.convertModelToResponse(
                productService.createProduct(productMapper.convertRequestToModel(body))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productMapper.convertModelListToResponseList(
                productService.getAllProducts()
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable Integer id, @RequestBody ProductRequest body){
        return productMapper.convertModelToResponse(
                productService.updateProduct(id, body)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse deleteProduct(@PathVariable Integer id){
        return productMapper.convertModelToResponse(
                productService.deleteProduct(id)
        );
    }
}