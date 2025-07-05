package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductNameCommand {
    private final String subsidiaryId;
    private final String productId;
    private final String name;
    
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Product name must be at least 3 characters long");
        }
        if (name.trim().length() > 255) {
            throw new IllegalArgumentException("Product name cannot exceed 255 characters");
        }
    }
}
