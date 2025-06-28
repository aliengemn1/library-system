package com.example.library.admin.model;

public enum AdminRole {
    SUPER_ADMIN("Super Administrator"),
    ADMIN("Administrator"),
    LIBRARIAN("Librarian"),
    ASSISTANT("Assistant");
    
    private final String displayName;
    
    AdminRole(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 