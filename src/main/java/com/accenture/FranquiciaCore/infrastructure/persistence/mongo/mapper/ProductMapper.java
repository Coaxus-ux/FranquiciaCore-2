package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;
import org.bson.types.ObjectId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;

public class ProductMapper {
    public static Product toDomain(ProductDocument doc) {
        return Product.builder()
                .id(new ProductId(doc.getId().toString()))
                .name(doc.getName())
                .category(CategoryProduct.valueOf(doc.getCategory()))
                .stock(Stock.builder()
                        .id(new StockId(doc.getStock().getId().toString()))
                        .quantity(doc.getStock().getQuantity())
                        .build())
                .subsidiaryId(new SubsidiaryId(doc.getSubsidiary().getId().toString()))
                .build();
    }

    public static ProductDocument toDocument(Product product) {
        return ProductDocument.builder()
                .id(new ObjectId(product.getId().toString()))
                .name(product.getName())
                .category(product.getCategory().name())
                .stock(StockDocument.builder().quantity(product.getStock().getQuantity()).build())
                .subsidiary(SubsidiaryDocument.builder()
                    .id(new ObjectId(product.getSubsidiaryId().toString()))
                    .name(product.getSubsidiaryId().toString())
                    .build())
                .build();
    }
}
