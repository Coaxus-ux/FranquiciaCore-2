package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidaryDocument;

public class SubsidaryMapper {
  public static Subsidiary toDomain(SubsidaryDocument doc) {
    return Subsidiary.builder()
        .id(doc.getId())
        .name(doc.getName())
        .franchiseId(doc.getFranchiseId())
        .build();
  }

  public static SubsidaryDocument toDocument(Subsidiary subsidiary) {
    return SubsidaryDocument.builder()
        .id(subsidiary.getId())
        .name(subsidiary.getName())
        .franchiseId(subsidiary.getFranchiseId())
        .build();
  }
}
