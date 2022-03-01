package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.ConfectionRequest;
import com.stock.sweet.sweetstockapi.dto.response.ConfectionResponse;
import com.stock.sweet.sweetstockapi.model.Confection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ConfectionMapper {
    public Confection convertRequestToModel(ConfectionRequest confectionRequest) {
        return new Confection(
                null,
                UUID.randomUUID().toString(),
                confectionRequest.getDate(),
                confectionRequest.getQuantityIngredient(),
                confectionRequest.getCost(),
                confectionRequest.getEmployee()
        );
    }

    public ConfectionResponse convertModelToResponse(Confection confection) {
        return new ConfectionResponse(
                confection.getUuid(),
                confection.getDate(),
                confection.getQuantityIngredient(),
                confection.getCost(),
                confection.getEmployee()
        );
    }

    public List<ConfectionResponse> convertModelListToResponseList(List<Confection> confections) {
        return confections.stream().map(c -> {
            return new ConfectionResponse(
                    c.getUuid(),
                    c.getDate(),
                    c.getQuantityIngredient(),
                    c.getCost(),
                    c.getEmployee()
            );
        }).collect(Collectors.toList());
    }
}