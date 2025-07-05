package com.accenture.franquiciaCore.application.subsidiary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddSubsidiaryCommand {
    private final String franchiseId;
    private final String name;
}
