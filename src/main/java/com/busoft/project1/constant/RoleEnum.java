package com.busoft.project1.constant;

public enum RoleEnum {

    ADMIN("ADMIN"),SUPER_ADMIN("SUPER_ADMIN");
    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
