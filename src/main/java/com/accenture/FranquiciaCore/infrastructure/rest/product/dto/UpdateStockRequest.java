package com.accenture.franquiciaCore.infrastructure.rest.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStockRequest {
  @NotNull(message = "Quantity is required")
  @Min(value = 1, message = "Quantity must be greater than 0")
  private int quantity;
}
