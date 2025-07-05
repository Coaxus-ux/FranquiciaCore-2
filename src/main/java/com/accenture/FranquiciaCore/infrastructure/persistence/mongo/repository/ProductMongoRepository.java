package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;

import reactor.core.publisher.Flux;
import org.bson.types.ObjectId;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, ObjectId> {
    Flux<ProductDocument> findBySubsidiaryId(ObjectId subsidiaryId);
}
