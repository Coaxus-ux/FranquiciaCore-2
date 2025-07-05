package com.accenture.franquiciaCore.application.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateFranchiseCommand {
  private final String id;
  private final String name;
}
