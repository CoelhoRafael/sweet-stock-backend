package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.EmployeRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientResponse;
import com.stock.sweet.sweetstockapi.dto.response.UserResponse;
import com.stock.sweet.sweetstockapi.model.Employee;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                null,
                false
        );
    }

    public List<UserResponse> convertListModelToResponse(List<User> allWaitingApproval) {
        return allWaitingApproval.stream().map(user ->
                UserResponse.builder()
                        .uuid(user.getUuid())
                        .name(user.getName())
                        .email(user.getEmail())
                        .telephoneNumber(user.getTelephoneNumber())
                        .levelAccess(user.getLevelAccess())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<UserResponse> convertModelListToResponseList(List<User> users) {
        return users.stream().map(u -> {
            return new UserResponse(
                    u.getUuid(),
                    u.getName(),
                    u.getEmail(),
                    u.getTelephoneNumber(),
                    u.getLevelAccess(),
                    u.isAproved()
            );
        }).collect(Collectors.toList());
    }
}
