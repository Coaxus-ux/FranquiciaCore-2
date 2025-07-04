package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository;

import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.StockDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StockMongoRepository extends ReactiveMongoRepository<StockDocument, String> {
  Mono<StockDocument> findByProductId(String productId);
}
