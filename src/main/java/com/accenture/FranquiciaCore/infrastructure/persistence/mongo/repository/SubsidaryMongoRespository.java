package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidaryDocument;

import reactor.core.publisher.Flux;

public interface SubsidaryMongoRespository extends ReactiveMongoRepository<SubsidaryDocument, String> {
  Flux<SubsidaryDocument> findByFranchiseId(String franchiseId);
} 
