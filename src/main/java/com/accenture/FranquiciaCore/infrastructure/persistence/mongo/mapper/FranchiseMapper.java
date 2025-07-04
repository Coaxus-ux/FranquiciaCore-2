package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;

public class FranchiseMapper {
    public static Franchise toDomain(FranchiseDocument doc) {
        return Franchise.builder()
                .id(new FranchiseId(doc.getId()))
                .name(doc.getName())
                .build();
    }

    public static FranchiseDocument toDocument(Franchise franchise) {
        return FranchiseDocument.builder()
                .id(franchise.getId().toString())
                .name(franchise.getName())
                .build();
    }
}
