package com.andersen.tickettoride.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    TRAVELLER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
