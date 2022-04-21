package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.OutStock;
import com.stock.sweet.sweetstockapi.repository.OutStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutStockService {
    @Autowired
    private OutStockRepository outStockRepository;

    public List<OutStock> getOutStockCurrentMonth(String idCompany) {
        return outStockRepository.findAllByIdCompany(idCompany)
                .stream()
                .filter(outStock -> outStock.getDate().withDayOfMonth(1).equals(LocalDate.now().withDayOfMonth(1)))
                .collect(Collectors.toList());
    }

}
