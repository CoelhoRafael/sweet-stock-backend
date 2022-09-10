package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProductIngredientRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.ConfectionRepository;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Product createProduct(Product product, List<ProductIngredientRequest> ingredients) {
        List<Ingredient> ingredientsModels = new ArrayList<>();

        ingredients.forEach(ingredient ->
                ingredientsModels.add(ingredientRepository.findIngredientByUuid(ingredient.getUuidIngredient()))
        );

        ingredientsModels.forEach(ingredient -> {

            var x = ingredients.stream().filter(i -> i.getUuidIngredient().equals(ingredient.getUuid())).collect(Collectors.toList()).get(0);
            product.getConfections().add(
                    Confection.builder()
                            .uuid(UUID.randomUUID().toString())
                            .cost(0.0)
                            .quantity(x.getQuantity())
                            .product(product)
                            .ingredient(ingredient)
                            .date(LocalDate.now())
                            .build()
            );
            ingredient.setTotal(ingredient.getTotal() - x.getQuantity());
        });

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
        productToUpdate.setCategory(productRequest.getCategory().toString());

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