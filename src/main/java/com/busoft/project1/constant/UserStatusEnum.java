package com.busoft.project1.constant;

public enum UserStatusEnum {

    ACTIVE("ACTIVE"),INACTIVE("INACTIVE"),
    DELETED("DELETED");

    private String key;

    UserStatusEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}