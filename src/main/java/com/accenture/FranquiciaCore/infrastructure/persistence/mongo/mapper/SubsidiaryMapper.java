package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;

public class SubsidiaryMapper {
  public static Subsidiary toDomain(SubsidiaryDocument doc) {
    return Subsidiary.builder()
        .id(new SubsidiaryId(doc.getId()))
        .name(doc.getName())
        .franchiseId(new FranchiseId(doc.getFranchiseId()))
        .build();
  }

  public static SubsidiaryDocument toDocument(Subsidiary subsidiary) {
    return SubsidiaryDocument.builder()
        .id(subsidiary.getId().toString())
        .name(subsidiary.getName())
        .franchiseId(subsidiary.getFranchiseId().toString())
        .build();
  }
}
