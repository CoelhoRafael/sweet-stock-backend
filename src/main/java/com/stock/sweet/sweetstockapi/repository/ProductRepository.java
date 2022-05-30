package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("update Product p set p.total = ?2 where p.uuid = ?1")
    void sellProduct(String uuid, Double total);

}