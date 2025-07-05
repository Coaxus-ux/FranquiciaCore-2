package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubsidiaryTopProduct {
    @NotBlank(message = "Subsidiary id is required")
    private String subsidiaryId;
    @NotBlank(message = "Subsidiary name is required")
    private String subsidiaryName;
    @NotEmpty(message = "Top product is required")
    private TopProductDto topProduct;
}
