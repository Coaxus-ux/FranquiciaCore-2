package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;
import org.bson.types.ObjectId;

public class SubsidiaryMapper {
  public static Subsidiary toDomain(SubsidiaryDocument doc) {
    return Subsidiary.builder()
        .id(new SubsidiaryId(doc.getId().toString()))
        .name(doc.getName())
        .franchiseId(new FranchiseId(doc.getFranchise().getId().toString()))
        .build();
  }

  public static SubsidiaryDocument toDocument(Subsidiary subsidiary) {
    return SubsidiaryDocument.builder()
        .id(new ObjectId(subsidiary.getId().toString()))
        .name(subsidiary.getName())
        .franchise(new FranchiseDocument(new ObjectId(subsidiary.getFranchiseId().toString()), subsidiary.getFranchiseId().toString()))
        .build();
  }
}
