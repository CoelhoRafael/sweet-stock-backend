package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByUuid(String uuid);

    List<Product> findAllBySoldIsFalse();
    @Modifying
    @Transactional
    @Query("update Product p set p.total = ?2, p.sold = true, p.dateUpdate = ?3 where p.uuid = ?1")
    void sellProduct(String uuid, Integer total, LocalDate saleValue);

    @Query("select p from Product p where p.company.uuid = ?1 and p.total > 0")
    List<Product> findAllProductsNoSold(String uuid);

    @Query("select p from Product p where p.company.uuid = ?1 and p.sold = true and p.dateOfSale is not null")
    List<Product> findAllProductsSold(String uuid);


    @Query("select p from Product p where p.sold = false and p.dateOfSale is null and p.category = ?1")
    List<Product> getAllProductsNoSoldByCategory(String category);

    @Query("select p from Product p where p.uuid in ?1")
    List<Product> findByUuids(List<String> uuids);

    Product getByUuid(String uuidProduct);

    @Query("select p from Product p where p.company.uuid = ?1")
    List<Product> getAllProductsByUuidCompany(String uuid);




}