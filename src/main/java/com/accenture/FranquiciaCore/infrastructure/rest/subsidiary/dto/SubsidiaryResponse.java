package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubsidiaryResponse {
  private String id;
  private String name;
  private String franchiseId;
}
