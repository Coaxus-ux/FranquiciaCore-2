package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository;

import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Flux<ProductDocument> findByFranchiseId(String franchiseId);
    Flux<ProductDocument> findBySubsidiaryId(String subsidiaryId);
}
