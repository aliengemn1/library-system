package com.example.library.admin.model;

public enum AdminStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    LOCKED("Locked");
    
    private final String displayName;
    
    AdminStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 