package com.busoft.project1.constant;

public enum StatusEnum {
    SUCCESS("SUCCESS"),FAILED("FAILED");

    private String key;

    StatusEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

