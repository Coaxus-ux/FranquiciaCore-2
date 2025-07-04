package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private String category;
    private StockDocument stock;
}
