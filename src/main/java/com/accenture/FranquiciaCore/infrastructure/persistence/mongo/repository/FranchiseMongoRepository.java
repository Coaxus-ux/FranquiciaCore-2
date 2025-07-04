package com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;

public interface FranchiseMongoRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}
