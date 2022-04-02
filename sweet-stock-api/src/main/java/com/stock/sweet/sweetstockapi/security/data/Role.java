package com.stock.sweet.sweetstockapi.security.data;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Role(String name) {
        this.name = name;
    }
}
