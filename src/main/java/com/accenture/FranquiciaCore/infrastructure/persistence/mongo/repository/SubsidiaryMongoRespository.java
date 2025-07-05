package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.SubsidiaryDocument;

import reactor.core.publisher.Flux;

public interface SubsidiaryMongoRespository extends ReactiveMongoRepository<SubsidiaryDocument, String> {
  Flux<SubsidiaryDocument> findByFranchiseId(String franchiseId);
} 
