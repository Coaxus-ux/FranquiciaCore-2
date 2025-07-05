package com.accenture.franquiciaCore.application.subsidiary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddSubsidiaryCommand {
    private final String franchiseId;
    private final String name;
    
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subsidiary name cannot be empty");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Subsidiary name must be at least 3 characters long");
        }
        if (name.trim().length() > 255) {
            throw new IllegalArgumentException("Subsidiary name cannot exceed 255 characters");
        }
        if (franchiseId == null || franchiseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Franchise ID cannot be empty");
        }
    }
}
