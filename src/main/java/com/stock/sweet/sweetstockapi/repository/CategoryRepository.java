package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("select count(c) from Category c where c.id = ?1")
    long countById(String id);
}