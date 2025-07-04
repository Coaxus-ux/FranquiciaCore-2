package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository;

import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.SubsidaryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SubsidaryMongoRespository extends ReactiveMongoRepository<SubsidaryDocument, String> {
  Flux<SubsidaryDocument> findByFranchiseId(String franchiseId);
} 
