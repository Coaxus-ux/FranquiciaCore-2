package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;

import reactor.core.publisher.Mono;

public interface StockMongoRepository
    extends ReactiveMongoRepository<StockDocument, ObjectId> {

  // Spring Data generará internamente { "productId": ?0 }
  Mono<StockDocument> findByProductId(ObjectId productId);

  Mono<Void> deleteByProductId(ObjectId productId);
}
