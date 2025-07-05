package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubsidiaryRequest {
    private String franchiseId;
    private String name;
}
