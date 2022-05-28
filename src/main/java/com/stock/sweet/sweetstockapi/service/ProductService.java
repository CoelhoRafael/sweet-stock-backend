package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.IngredientConfection;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import com.stock.sweet.sweetstockapi.utils.FilaObj;
import com.stock.sweet.sweetstockapi.utils.Operation;
import com.stock.sweet.sweetstockapi.utils.OperationType;
import com.stock.sweet.sweetstockapi.utils.StackObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private HashMap<String, FilaObj<IngredientConfection>> hashMapQueue = new HashMap<String, FilaObj<IngredientConfection>>();

    private HashMap<String, StackObj<Operation>> hashMapStack = new HashMap<String, StackObj<Operation>>();

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
        productToUpdate.setDateInsert(productRequest.getDateInsert());
        productToUpdate.setDateUpdate(productRequest.getDateUpdate());
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

    public boolean addIngredientToQueue(String companyUuid, Ingredient ingredientSaved, IngredientConfection ingredientConfection) {
        try {
            if (hashMapQueue.containsKey(companyUuid) && hashMapStack.containsKey(companyUuid)) {
                hashMapQueue.get(companyUuid).insert(ingredientConfection);
                Operation operation = new Operation(ingredientConfection.getUuidIngredient(), OperationType.ADD_INGREDIENT);
                hashMapStack.get(companyUuid).push(operation);
            } else {
                FilaObj<IngredientConfection> ingredientQueue = new FilaObj(30);
                ingredientQueue.insert(ingredientConfection);
                hashMapQueue.put(companyUuid, ingredientQueue);

                StackObj<Operation> operacaoStack = new StackObj<>(25);
                Operation operation = new Operation(ingredientConfection.getUuidIngredient(), OperationType.ADD_INGREDIENT);
                operacaoStack.push(operation);
                hashMapStack.put(companyUuid, operacaoStack);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}