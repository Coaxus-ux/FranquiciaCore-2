package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class FranchiseTopProductsResponse {
    private String franchiseId;
    private String franchiseName;
    private List<SubsidiaryTopProduct> subsidiaries;
}
