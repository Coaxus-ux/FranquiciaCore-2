package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;

import reactor.core.publisher.Flux;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Flux<ProductDocument> findByFranchiseId(String franchiseId);
    Flux<ProductDocument> findBySubsidiaryId(String subsidiaryId);
}
