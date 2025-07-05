package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductCommand {
  private String name;
  private String subsidiaryId;
  private String category;
  private int quantity;
  
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
    if (category == null || category.trim().isEmpty()) {
      throw new IllegalArgumentException("Product category cannot be empty");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Product quantity must be greater than 0");
    }
  }
}
