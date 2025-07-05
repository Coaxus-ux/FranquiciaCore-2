package com.accenture.franquiciaCore.infrastructure.persistence.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
@Document(collection = "subsidaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubsidiaryDocument {
  @Id
  private ObjectId id;
  private String name;
  
  private ObjectId franchiseId;
}
