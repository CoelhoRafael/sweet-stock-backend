package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}
