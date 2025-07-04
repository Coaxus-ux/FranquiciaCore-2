package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;

import reactor.core.publisher.Mono;

public interface StockMongoRepository extends ReactiveMongoRepository<StockDocument, String> {
  Mono<StockDocument> findByProductId(String productId);
}
