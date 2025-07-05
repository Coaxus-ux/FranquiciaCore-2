package com.accenture.franquiciaCore.application.franchise;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class CreateFranchiseCommand {
  private final String name;
}
