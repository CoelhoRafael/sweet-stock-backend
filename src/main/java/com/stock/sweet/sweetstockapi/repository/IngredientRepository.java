package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select i from Ingredient i where ?1 > i.expirationDate")
    List<Ingredient> findIngredientExpired(LocalDate now);

    @Modifying
    @Transactional
    @Query("update Ingredient i set i.expirated = true where i.uuid = ?1 and i.expirated = false")
    void updateForTrueExpiratedIngredient(String uuid);

    @Query("select i from Ingredient i where ?1 > i.expirationDate and i.viewInReports = true")
    List<Ingredient> findExpiredIngredientsForDays(LocalDate daysToGenerateReport);
}