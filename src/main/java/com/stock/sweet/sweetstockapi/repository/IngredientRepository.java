package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    List<Ingredient> findExpiredIngredientsByDate(LocalDate daysToGenerateReport);

    List<Ingredient> findAllByUuidCompany(String uuid);

    Ingredient findIngredientByUuid (String uuid);

    @Query("select i from Ingredient i where i.uuidCompany = ?1 and i.expirationDate < ?2 and i.expirated = false")
    List<Ingredient> getAllIngredientsNearExpire(String uuid, LocalDate expirationDate);

    @Query("select i from Ingredient i where i.uuidCompany = ?1 and i.expirationDate < ?2")
    List<Ingredient> expiredIngredients(String uuid, LocalDate expirationDate);

}