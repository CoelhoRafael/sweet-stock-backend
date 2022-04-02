package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.EmployeRequest;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeMapper {
    public User convertRequestToModel(EmployeRequest employeRequest) {
        return new User(
                null,
                UUID.randomUUID().toString(),
                employeRequest.getName(),
                employeRequest.getEmail(),
                employeRequest.getPassword(),
                employeRequest.getTelephoneNumber(),
                LevelAccess.EMPLOYEE_NOT_VERIFIED.name(),
                null
        );
    }
}
