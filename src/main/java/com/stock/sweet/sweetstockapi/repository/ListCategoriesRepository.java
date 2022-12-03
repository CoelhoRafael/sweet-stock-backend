package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.ListCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListCategoriesRepository extends JpaRepository<ListCategories, Integer> {

    @Query("select l from ListCategories l where l.uuidCompany = ?1")
    List<ListCategories> findByUuidCompany(String uuidCompany);

}
