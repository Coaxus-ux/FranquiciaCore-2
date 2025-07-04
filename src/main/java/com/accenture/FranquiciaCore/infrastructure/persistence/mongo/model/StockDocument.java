package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDocument {
    @Id
    private String id;
    private String productId;
    private int quantity;
}
