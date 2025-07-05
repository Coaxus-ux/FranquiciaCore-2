package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductNameCommand {
    private final String subsidiaryId;
    private final String productId;
    private final String name;
}
