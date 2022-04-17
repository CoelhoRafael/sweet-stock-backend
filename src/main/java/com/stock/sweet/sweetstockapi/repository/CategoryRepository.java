package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByUuid(String uuid);
}