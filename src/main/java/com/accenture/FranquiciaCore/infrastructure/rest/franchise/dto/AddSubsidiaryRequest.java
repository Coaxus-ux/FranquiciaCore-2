package com.accenture.franquiciaCore.infrastructure.rest.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSubsidiaryRequest {
    private String franchiseId;
    private String name;
}
