package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubsidiaryTopProduct {
    private String subsidiaryId;
    private String subsidiaryName;
    private TopProductDto topProduct;
}
