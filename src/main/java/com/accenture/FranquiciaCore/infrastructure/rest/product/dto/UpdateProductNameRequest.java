package com.accenture.franquiciaCore.infrastructure.rest.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProductNameRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
  private String name;
}
