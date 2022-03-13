package com.stock.sweet.sweetstockapi.model;

import java.util.List;

public class Administrator extends User{
    private List<Employee> listEmployee;

    @Override
    public String login(String email, String password) {
        return null;
    }
}
