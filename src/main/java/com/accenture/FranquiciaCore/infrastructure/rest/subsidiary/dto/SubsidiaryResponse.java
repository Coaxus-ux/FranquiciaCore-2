package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubsidiaryResponse {
  @NotBlank(message = "Id is required")
  private String id;
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
  private String name;
  @NotBlank(message = "Franchise id is required")
  private String franchiseId;
}
