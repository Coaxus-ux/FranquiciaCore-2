package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteProductCommand {
    private final String subsidiaryId;
    private final String productId;
}
