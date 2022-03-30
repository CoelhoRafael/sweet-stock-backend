package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByUuid(String uuid);
}