package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.ConfectionRepository;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ConfectionRepository confectionRepository;

    @Autowired
    private IngredientService ingredientService;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductByUuid(String uuid) throws NotFoundException {
        return productRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Produto não encontrado.")
        );
    }

    public Product updateProduct(String uuid, ProductRequest productRequest) throws Exception {
        Product productToUpdate = findProductByUuid(uuid);

        if (productToUpdate == null) {
            return null;
        }

        productToUpdate.setName(productRequest.getName());
        productToUpdate.setSaleValue(productRequest.getSaleValue());
        productToUpdate.setExpirationDate(productRequest.getExpirationDate());
        productToUpdate.setCategory(productRequest.getCategory());

        productRepository.save(productToUpdate);

        return productToUpdate;
    }

    public Product deleteProduct(String uuid) throws Exception {
        Product productToDelete = findProductByUuid(uuid);

        if (productToDelete == null) {
            return null;
        }

        productRepository.delete(productToDelete);
        return productToDelete;
    }

    public ProductRequestSell sellProduct(String uuidProduct, Double soldQuantity) throws Exception {
        Product p = productRepository.findByUuid(uuidProduct).get();
        if (p.getTotal() < soldQuantity || p.getTotal() == 0) {
            p.setSold(true);
            throw new Exception("Produto esgotado");
        }
        double newValue = (p.getTotal() - soldQuantity);
        productRepository.sellProduct(uuidProduct, newValue);
        return null;
    }
}