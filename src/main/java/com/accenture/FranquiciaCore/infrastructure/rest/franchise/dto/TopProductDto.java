package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TopProductDto {
    @NotBlank(message = "Product id is required")
    private String productId;
    @NotBlank(message = "Product name is required")
    private String productName;
    @NotBlank(message = "Stock is required")
    private int stock;
}
