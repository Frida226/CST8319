package com.flowerorder.model;

public enum Role {
    USER,
    ADMIN;
    
    public static Role fromString(String roleStr) {
        if (roleStr != null) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(roleStr)) {
                    return role;
                }
            }
        }
        return null; // Optionally throw an IllegalArgumentException if the role string is invalid
    }
}
