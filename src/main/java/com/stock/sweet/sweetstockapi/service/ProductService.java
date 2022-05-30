package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.IngredientConfection;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.ConfectionRepository;
import com.stock.sweet.sweetstockapi.repository.IngredientRepository;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import com.stock.sweet.sweetstockapi.utils.FilaObj;
import com.stock.sweet.sweetstockapi.utils.Operation;
import com.stock.sweet.sweetstockapi.utils.OperationType;
import com.stock.sweet.sweetstockapi.utils.StackObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    private HashMap<String, FilaObj<IngredientConfection>> hashMapQueue = new HashMap<String, FilaObj<IngredientConfection>>();

    private HashMap<String, StackObj<Operation>> hashMapStack = new HashMap<String, StackObj<Operation>>();

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllBySoldIsFalse();
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

    public boolean addIngredientToConfectionQueue(String companyUuid, Ingredient ingredientSaved, IngredientConfection ingredientConfection) {
        try {
            if (hashMapQueue.containsKey(companyUuid) && hashMapStack.containsKey(companyUuid)) {
                hashMapQueue.get(companyUuid).insert(ingredientConfection);
                Operation operation = new Operation(
                        ingredientConfection.getUuidIngredient(),
                        OperationType.ADD_INGREDIENT,
                        ingredientConfection.getAmount()
                );
                hashMapStack.get(companyUuid).push(operation);
            } else {
                FilaObj<IngredientConfection> ingredientQueue = new FilaObj(30);
                ingredientQueue.insert(ingredientConfection);
                hashMapQueue.put(companyUuid, ingredientQueue);

                StackObj<Operation> operacaoStack = new StackObj<>(25);
                Operation operation = new Operation(
                        ingredientConfection.getUuidIngredient(),
                        OperationType.ADD_INGREDIENT,
                        ingredientConfection.getAmount()
                );
                operacaoStack.push(operation);
                hashMapStack.put(companyUuid, operacaoStack);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Ingredient> getAllIngredientsFromConfectionQueue(String companyUuid) {
        List<Ingredient> ingredients = new ArrayList<>();
        FilaObj<IngredientConfection> queue = new FilaObj<>(30);
        queue = hashMapQueue.get(companyUuid);

        if (queue == null) return ingredients;

        queue.retornarTodos().forEach(ingredientConfection -> {
            ingredients.add(ingredientService.getIngredientByUuid(ingredientConfection.getUuidIngredient()));
        });

        return ingredients;
    }

    public Boolean undoLastIngredientConfetionOperation(String companyUuid) {
        var operationStack = hashMapStack.get(companyUuid);
        var lastOperation = operationStack.pop();

        var reverseOperation = lastOperation.getOperationType().getUndoOperation();

        switch (reverseOperation) {
            case ADD_INGREDIENT:
                hashMapQueue.get(companyUuid).insert(
                        new IngredientConfection(lastOperation.getAlteredItemUuid(), lastOperation.getNumberUnits()));
                return true;
            case REMOVE_INGREDIENT:
                hashMapQueue.get(companyUuid).poll();
                return true;
            default:
                return false;
        }
    }

    public Boolean removeLastIngredientConfection(String companyUuid) {
        if (hashMapQueue.containsKey(companyUuid) && hashMapStack.containsKey(companyUuid)) {
            if (hashMapQueue.get(companyUuid).isEmpty()) {
                return false;
            }
            var lastIngredientConfectionAdded = hashMapQueue.get(companyUuid).poll();
            Operation operation = new Operation(
                    lastIngredientConfectionAdded.getUuidIngredient(),
                    OperationType.REMOVE_INGREDIENT,
                    lastIngredientConfectionAdded.getAmount()
            );
            hashMapStack.get(companyUuid).push(operation);
            return true;
        }
        return false;
    }

    public Boolean finishAndCreateProduct(String companyUuid, Product product) {
        if (hashMapQueue.containsKey(companyUuid) && hashMapStack.containsKey(companyUuid)) {
            if (hashMapQueue.get(companyUuid).isEmpty()) {
                return false;
            }

            var stack = hashMapStack.get(companyUuid);
            var queue = hashMapQueue.get(companyUuid);

            var ingredientConfectionList = queue.retornarTodos();

            ingredientConfectionList.forEach(ingredientConfection -> {
                Ingredient ingredient = ingredientService.getIngredientByUuid(ingredientConfection.getUuidIngredient());
                ingredient.setQuantityUsed(ingredient.getQuantityUsed() - ingredientConfection.getAmount());
                ingredient.setDateUpdate(LocalDate.now());
                ingredientRepository.save(ingredient);

                Confection confection =
                        Confection.builder()
                                .uuid(UUID.randomUUID().toString())
                                .cost(0.00)
                                .quantity(ingredientConfection.getAmount())
                                .date(LocalDate.now())
                                .product(product)
                                .ingredient(ingredient)
                                .build();

                confectionRepository.save(confection);
            });
        }
        return true;
    }

    public Boolean removeIngredientFromQueueByUuid(String uuidIngredient, String companyUuid) {
        return false;
    }

    public ProductRequestSell sellProduct(String uuidProduct, Double soldQuantity) throws Exception {
        Product p= productRepository.findByUuid(uuidProduct).get();
        if (p.getTotal() < soldQuantity || p.getTotal() == 0) {
            p.setSold(true);
            throw new Exception("Produto esgotado");
        }
        double newValue =(p.getTotal() - soldQuantity);
        productRepository.sellProduct(uuidProduct,newValue);
       return null;
    }
}