package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franchises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchiseDocument {
    @Id
    private String id;
    private String name;
}
