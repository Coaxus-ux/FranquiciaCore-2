package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;
import org.bson.types.ObjectId;

public class FranchiseMapper {
    public static Franchise toDomain(FranchiseDocument doc) {
        return Franchise.builder()
                .id(new FranchiseId(doc.getId().toString()))
                .name(doc.getName())
                .build();
    }

    public static FranchiseDocument toDocument(Franchise franchise) {
        return FranchiseDocument.builder()
                .id(new ObjectId(franchise.getId().toString()))
                .name(franchise.getName())
                .build();
    }
}
