package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.mapper.ProductMapper;
import com.stock.sweet.sweetstockapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest body) {
        return productMapper.convertModelToResponse(
                productService.createProduct(productMapper.convertRequestToModel(body))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProviders() {
        return productMapper.convertModelListToResponseList(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductByUuid(@PathVariable String uuid) throws Exception {
        return productMapper.convertModelToResponse(
                productService.findProductByUuid(uuid)
        );
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable String uuid, @RequestBody ProductRequest body) throws Exception {
        return productMapper.convertModelToResponse(
                productService.updateProduct(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponse deleteProduct(@PathVariable String uuid) throws Exception {
        return productMapper.convertModelToResponse(
                productService.deleteProduct(uuid)
        );
    }
}