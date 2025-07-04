package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidaryDocument;

public class SubsidaryMapper {
  public static Subsidiary toDomain(SubsidaryDocument doc) {
    return Subsidiary.builder()
        .id(new SubsidiaryId(doc.getId()))
        .name(doc.getName())
        .franchiseId(new FranchiseId(doc.getFranchiseId()))
        .build();
  }

  public static SubsidaryDocument toDocument(Subsidiary subsidiary) {
    return SubsidaryDocument.builder()
        .id(subsidiary.getId().toString())
        .name(subsidiary.getName())
        .franchiseId(subsidiary.getFranchiseId().toString())
        .build();
  }
}
