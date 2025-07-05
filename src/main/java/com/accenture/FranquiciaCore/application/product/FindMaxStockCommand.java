package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindMaxStockCommand {
    private final String franchiseId;
}
