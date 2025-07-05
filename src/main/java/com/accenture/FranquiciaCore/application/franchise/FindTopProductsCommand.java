package com.accenture.franquiciaCore.application.franchise;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class FindTopProductsCommand {
    private final String franchiseId;
}
