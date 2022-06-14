package com.myproject.pronajdimajstor.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, CLIENT, MAJSTOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
