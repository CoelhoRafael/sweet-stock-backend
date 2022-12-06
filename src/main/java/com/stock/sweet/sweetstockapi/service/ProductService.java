package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.OutStockRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductIngredientRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProductRequestSell;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.exception.BadRequestException;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.mapper.OutStockMapper;
import com.stock.sweet.sweetstockapi.mapper.ProductMapper;
import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ConfectionRepository confectionRepository;

    @Autowired
    private OutStockRepository outStockRepository;

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private OutStockService outStockService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OutStockMapper outStockMapper;

    @Autowired
    private CompanyRepository companyRepository;

    public Product createProduct(Product product, List<ProductIngredientRequest> ingredients, String uuidCompany) throws NotFoundException, BadRequestException {
        var company = companyRepository.findByUuid(uuidCompany).get();
        product.setCompany(company);

        List<Ingredient> ingredientsFound = new ArrayList<>();
    
        for (ProductIngredientRequest i : ingredients) {
            findIngredients(ingredientsFound, i);
        }

        for (Ingredient ingredientFound : ingredientsFound) {
            var ingredientToConfection = ingredients
                    .stream()
                    .filter(i -> i.getUuidIngredient().equals(ingredientFound.getUuid()))
                    .findFirst().get();

            var totalUsed = ingredientFound.getQuantityUsed() + ingredientToConfection.getQuantity();
            if (totalUsed > ingredientFound.getTotal()) {
                throw new BadRequestException(String.format("Erro ao criar produto. Ingrediente: %s. Quantidade maior do" +
                        " que a presente em estoque.", ingredientFound.getName()));
            }

            var newConfection = Confection.builder()
                    .uuid(UUID.randomUUID().toString())
                    .cost(getConfectionCost(ingredientFound, ingredientToConfection))
                    .quantity(ingredientToConfection.getQuantity())
                    .product(product)
                    .ingredient(ingredientFound)
                    .date(LocalDate.now())
                    .build();

            product.getConfections().add(newConfection);
            ingredientFound.setQuantityUsed(ingredientFound.getQuantityUsed() + ingredientToConfection.getQuantity());

            ingredientRepository.save(ingredientFound);
        }

        productRepository.save(product);
        confectionRepository.saveAll(product.getConfections());
        return product;
    }

    private Double getConfectionCost(Ingredient ingredient, ProductIngredientRequest ingredientToConfection) {
        return ingredient.getTotal() / ingredient.getBuyValue() * ingredientToConfection.getQuantity();
    }

    private void findIngredients(List<Ingredient> ingredientsModels, ProductIngredientRequest i) throws NotFoundException {
        Ingredient findIngredient = ingredientRepository.findIngredientByUuid(i.getUuidIngredient()).orElseThrow(
                () -> new NotFoundException("Falha ao buscar ingrediente. Id: " + i.getUuidIngredient()));
        ingredientsModels.add(findIngredient);
    }

    public List<Product> getAllProducts(String uuidCompany) {
        return productRepository.getAllProductsByUuidCompany(uuidCompany);
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
        Product product = productRepository.findByUuid(uuidProduct).get();
        LocalDate data = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (product == null) {
            throw new Exception("Produto nao encontrado!");
        }

        if (product.getTotal() < soldQuantity || product.getTotal() == 0) {
            throw new Exception("Produto esgotado!");
        }
        OutStockRequest outStockRequest = new OutStockRequest(data,product.getCompany().getUuid(),product);
        Integer newValue = (product.getTotal().intValue() - soldQuantity.intValue());
        productRepository.sellProduct(uuidProduct, newValue);
        outStockRepository.save(outStockMapper.convertRequestToModel(outStockRequest));
        return new ProductRequestSell(soldQuantity);
    }

    public List<Product> getAllProductsNoSold(String uuidCompany) {
        return productRepository.findAllProductsNoSold(uuidCompany);
    }

    public List<Product> getAllProductsSold(String uuidCompany) {
        return productRepository.findAllProductsSold(uuidCompany);
    }
    public Integer getAllProductsSoldOnMonth(String uuidCompany) {
        return outStockService.allItemsSold(uuidCompany);
    }

    public ResponseEntity<List<ProductResponse>> getAllProductsNoSoldByCategory(String category) {
        List<Product> productList = productRepository.getAllProductsNoSoldByCategory(category);
        List<ProductResponse> productListResponse = productMapper.convertModelListToResponseList(productList);

        if (!productList.isEmpty()) {
            return ResponseEntity.status(200).body(productListResponse);
        }

        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<List<ProductResponse>> getProductsByUuids(List<String> uuids) throws Exception {
        if(uuids.isEmpty()){
            throw new Exception("É necessario enviar uuids para realizar a requisicao");
        }

        ArrayList<ProductResponse> productResponses = new ArrayList<>(
                productMapper.convertModelListToResponseList(productRepository.findByUuids(uuids))
        );

        if(productResponses.isEmpty()){
            throw new Exception("Nao foram encontrados produtos com os seguintes uuids: " + uuids);
        }
        return ResponseEntity.ok().body(productResponses);
    }
}