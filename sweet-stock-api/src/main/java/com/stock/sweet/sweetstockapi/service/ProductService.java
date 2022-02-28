package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Integer id, ProductRequest productRequest) {
        Product productToUpdate = getProductById(id);

        if (productToUpdate == null) {
            return null;
        }

        productToUpdate.setName(productRequest.getName());
        productToUpdate.setSaleValue(productRequest.getSaleValue());
        productToUpdate.setExpirationDate(productRequest.getExpirationDate());
        productToUpdate.setDateInsert(productRequest.getDateInsert());
        productToUpdate.setDateUpdate(productRequest.getDateUpdate());
        productToUpdate.setCategory(productRequest.getCategory());

        productRepository.save(productToUpdate);

        return productToUpdate;
    }

    public Product deleteProduct(Integer id){
        Product productToDelete = getProductById(id);

        if (productToDelete == null) {
            return null;
        }

        productRepository.deleteById(productToDelete.getId());

        return productToDelete;
    }

    public Product getProductById(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            return null;
        }
        return product.get();
    }
}