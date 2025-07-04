package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository;


import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FranchiseMongoRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}
