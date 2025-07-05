package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;
import org.bson.types.ObjectId;

public class SubsidiaryMapper {
  public static Subsidiary toDomain(SubsidiaryDocument doc) {
    return Subsidiary.builder()
        .id(new SubsidiaryId(doc.getId().toHexString()))
        .name(doc.getName())
        .franchiseId(new FranchiseId(doc.getFranchiseId().toHexString()))
        .build();
  }

  public static SubsidiaryDocument toDocument(Subsidiary sub) {
    return SubsidiaryDocument.builder()
        .id(new ObjectId(sub.getId().toString()))
        .name(sub.getName())
        .franchiseId(new ObjectId(sub.getFranchiseId().toString()))
        .build();
  }
}
