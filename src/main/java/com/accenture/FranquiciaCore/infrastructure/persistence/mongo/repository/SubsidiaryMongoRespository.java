package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;

import reactor.core.publisher.Flux;
import org.bson.types.ObjectId;

public interface SubsidiaryMongoRespository
    extends ReactiveMongoRepository<SubsidiaryDocument, ObjectId> {
  Flux<SubsidiaryDocument> findByFranchiseId(ObjectId franchiseId);
}
