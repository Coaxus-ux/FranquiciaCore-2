package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductStockCommand {
    private final String subsidiaryId;
    private final String productId;
    private final int quantity;
}
