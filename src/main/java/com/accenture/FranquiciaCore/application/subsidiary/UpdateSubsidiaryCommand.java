package com.accenture.franquiciaCore.application.subsidiary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubsidiaryCommand {
  private String id;
  private String name;
  private String franchiseId;
  
  public void validate() {
      if (name == null || name.trim().isEmpty()) {
          throw new IllegalArgumentException("Subsidiary name cannot be empty");
      }
      if (name.trim().length() < 3) {
          throw new IllegalArgumentException("Subsidiary name must be at least 3 characters long");
      }
      if (name.trim().length() > 255) {
          throw new IllegalArgumentException("Subsidiary name cannot exceed 255 characters");
      }
      if (franchiseId == null || franchiseId.trim().isEmpty()) {
          throw new IllegalArgumentException("Franchise ID cannot be empty");
      }
  }
}
