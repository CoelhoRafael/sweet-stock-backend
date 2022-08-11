package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestDashboard;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.mapper.ProductMapper;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.stock.sweet.sweetstockapi.service.ProductService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequestDashboard body) {
        return ResponseEntity.status(201).body(productMapper.convertModelToResponse(
                productService.createProduct(productMapper.convertRequestToModelDashboard(body))
        ));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.status(200).body(productMapper.convertModelListToResponseList(
                productService.getAllProducts()
        ));
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductByUuid(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.status(200).body(productMapper.convertModelToResponse(
                productService.findProductByUuid(uuid.toString())
        ));
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable String uuid, @RequestBody ProductRequest body) throws Exception {
        return productMapper.convertModelToResponse(
                productService.updateProduct(uuid, body)
        );
    }

    @PutMapping("/{soldQuantity}/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProductRequestSell sellProduct(@PathVariable String uuid, @PathVariable Double soldQuantity) throws Exception {
        return productService.sellProduct(uuid, soldQuantity);

    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponse deleteProduct(@PathVariable String uuid) throws Exception {
        return productMapper.convertModelToResponse(
                productService.deleteProduct(uuid)
        );
    }
}