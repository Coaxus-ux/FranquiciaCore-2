package com.accenture.franquiciaCore.infrastructure.rest.product.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductResponse {
    @NotBlank(message = "Id is required")
    private String id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;
    @NotBlank(message = "Category is required")
    @Size(min = 3, max = 255, message = "Category must be between 3 and 255 characters")
    private String category;
    @NotNull(message = "Stock is required")
    private int stock;
    @NotBlank(message = "Subsidiary id is required")
    private String subsidiaryId;
    @NotBlank(message = "Subsidiary name is required")
    private String subsidiaryName;
}
