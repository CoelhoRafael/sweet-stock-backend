package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Confection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfectionRepository extends JpaRepository<Confection, Integer> {
}