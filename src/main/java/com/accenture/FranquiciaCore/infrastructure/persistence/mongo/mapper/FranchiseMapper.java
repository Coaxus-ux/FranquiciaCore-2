package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.FranquiciaCore.domain.franchise.model.Franchise;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;

public class FranchiseMapper {
    public static Franchise toDomain(FranchiseDocument doc) {
        return Franchise.builder()
                .id(doc.getId())
                .name(doc.getName())
                .build();
    }

    public static FranchiseDocument toDocument(Franchise franchise) {
        return FranchiseDocument.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }
}
