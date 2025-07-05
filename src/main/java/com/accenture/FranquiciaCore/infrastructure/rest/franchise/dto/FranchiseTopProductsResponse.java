package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class FranchiseTopProductsResponse {
    @NotBlank(message = "Franchise id is required")
    private String franchiseId;
    @NotBlank(message = "Franchise name is required")
    private String franchiseName;
    @NotEmpty(message = "Subsidiaries are required")
    private List<SubsidiaryTopProduct> subsidiaries;
}
