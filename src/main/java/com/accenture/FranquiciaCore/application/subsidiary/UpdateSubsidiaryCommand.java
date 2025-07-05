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
}
