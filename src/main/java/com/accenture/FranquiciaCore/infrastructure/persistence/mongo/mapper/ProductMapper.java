package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;
import org.bson.types.ObjectId;

public class ProductMapper {

    // Document → Dominio (igual que antes)
    public static Product toDomain(ProductDocument doc) {
        Stock stock = null;
        if (doc.getStock() != null) {
            stock = Stock.builder()
                    .id(new StockId(doc.getStock().getId().toString()))
                    .quantity(doc.getStock().getQuantity())
                    .build()
                    .withProductId(new ProductId(doc.getId().toString())); // ojo: productId viene del propio doc.id
        }
        return Product.builder()
                .id(new ProductId(doc.getId().toString()))
                .name(doc.getName())
                .category(CategoryProduct.valueOf(doc.getCategory()))
                .stock(stock)
                .subsidiaryId(new SubsidiaryId(doc.getSubsidiaryId().toString()))
                .build();
    }

    public static ProductDocument toDocument(Product product) {
        ProductDocument.ProductDocumentBuilder builder = ProductDocument.builder()
            .id(new ObjectId(product.getId().getValue()))
            .name(product.getName())
            .category(product.getCategory().name())
            .subsidiaryId(new ObjectId(product.getSubsidiaryId().getValue()));
    
        if (product.getStock() != null) {
            Stock stock = product.getStock();
            builder.stock(
                StockDocument.builder()
                    // aquí usamos el ID del stock como _id
                    .id(new ObjectId(stock.getId().getValue()))
                    // y el productId lo sacamos de product.getId()
                    .productId(new ObjectId(product.getId().getValue()))
                    .quantity(stock.getQuantity())
                    .build()
            );
        }
    
        return builder.build();
    }
}
