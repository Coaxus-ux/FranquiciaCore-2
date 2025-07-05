package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDocument {
    @Id
    private ObjectId id;
    private ObjectId productId;
    private int quantity;
}
