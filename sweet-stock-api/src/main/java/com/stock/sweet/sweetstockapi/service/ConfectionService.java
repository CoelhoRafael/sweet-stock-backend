package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Confection;
import com.stock.sweet.sweetstockapi.repository.ConfectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfectionService {

    @Autowired
    private ConfectionRepository confectionRepository;

    public Confection createConfection(Confection confection){
        return confectionRepository.save(confection);
    }

    public List<Confection> getAllConfections(){
        return confectionRepository.findAll();
    }
}