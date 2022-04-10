package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select i from Ingredient i where ?1 > i.expirationDate")
    List<Ingredient> findIngredientExpired(LocalDate now);
}