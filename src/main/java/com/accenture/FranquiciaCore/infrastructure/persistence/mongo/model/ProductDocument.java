package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDocument {
    @Id
    private ObjectId id;
    private String name;
    private String category;
    private StockDocument stock;
    @DBRef(lazy = true)
    private SubsidiaryDocument subsidiary;
}
