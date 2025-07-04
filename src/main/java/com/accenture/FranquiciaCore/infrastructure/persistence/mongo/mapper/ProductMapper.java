package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;

public class ProductMapper {
    public static Product toDomain(ProductDocument doc) {
        return Product.builder()
                .id(doc.getId())
                .name(doc.getName())
                .category(CategoryProduct.valueOf(doc.getCategory()))
                .stock(Stock.builder()
                        .quantity(doc.getStock().getQuantity())
                        .build())
                .build();
    }

    public static ProductDocument toDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory().name())
                .stock(StockDocument.builder().quantity(product.getStock().getQuantity()).build())
                .build();
    }
}
