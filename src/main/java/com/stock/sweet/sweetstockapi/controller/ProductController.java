package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.mapper.IngredientMapper;
import com.stock.sweet.sweetstockapi.mapper.ProductMapper;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.stock.sweet.sweetstockapi.service.ProductService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body) throws Exception {
        return ResponseEntity.status(201).body(productMapper.convertModelToResponse(
                productService.createProduct(productMapper.convertRequestToModel(body), body.getIngredients())
        ));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.status(200).body(productMapper.convertModelListToResponseList(
                productService.getAllProducts()
        ));
    }

    @GetMapping("/products-no-sold")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProductsNoSold(
            @RequestHeader HttpHeaders headers
    ) throws JsonProcessingException {
        return ResponseEntity.status(200).body(productMapper.convertModelListToResponseList(
                productService.getAllProductsNoSold(headersUtils.getCompanyIdFromToken(headers))
        ));
    }

    @GetMapping("/products-no-sold-by-uuid-company/{uuidCompany}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProductsNoSoldByUuidCompany(@PathVariable String uuidCompany){
        return ResponseEntity.status(200).body(productMapper.convertModelListToResponseList(
                productService.getAllProductsNoSold(uuidCompany)
        ));
    }

    @GetMapping("/products-sold")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProductsSold(
            @RequestHeader HttpHeaders headers
    ) throws JsonProcessingException {
        return ResponseEntity.status(200).body(productMapper.convertModelListToResponseList(
                productService.getAllProductsSold(headersUtils.getCompanyIdFromToken(headers))
        ));
    }

    @GetMapping("/all-products-no-sold-by-category/{category}")
    public ResponseEntity<List<ProductResponse>> getAllProductsNoSoldByCategory(
            @RequestHeader HttpHeaders headers,
            @RequestParam String category
    ) throws JsonProcessingException {
        return productService.getAllProductsNoSoldByCategory(category);
    }


    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductByUuid(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.status(200).body(productMapper.convertModelToResponse(
                productService.findProductByUuid(uuid.toString())
        ));
    }

    @PostMapping("/get-products-by-uuids")
    public ResponseEntity<List<ProductResponse>> getProductsByUuids(@RequestBody List<String> uuids) throws Exception {
        return productService.getProductsByUuids(uuids);
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