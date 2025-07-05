package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;

import reactor.core.publisher.Mono;

public interface StockMongoRepository extends ReactiveMongoRepository<StockDocument, ObjectId> {
  Mono<StockDocument> findByProductId(ProductId productId);
  Mono<Void> deleteByProductId(ProductId productId);
}
