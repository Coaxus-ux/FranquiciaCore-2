package com.accenture.franquiciaCore.infrastructure.rest.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
  private String name;
  @NotBlank(message = "Category is required")
  @Size(min = 3, max = 255, message = "Category must be between 3 and 255 characters")
  private String category;
  @NotNull(message = "Quantity is required")
  @Min(value = 1, message = "Quantity must be greater than 0")
  private int quantity; 
}
