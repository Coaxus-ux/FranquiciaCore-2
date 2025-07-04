package com.accenture.franquiciaCore.infrastructure.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper.SubsidaryMapper;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository.SubsidaryMongoRespository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MongoSubsidaryRespository implements SubsidiaryRepository {
    private final SubsidaryMongoRespository repository;

    @Override
    public Mono<Subsidiary> findById(String id) {
        return repository.findById(id)
                .map(SubsidaryMapper::toDomain);
    }

    @Override
    public Mono<Subsidiary> save(Subsidiary subsidiary) {
        return repository.save(SubsidaryMapper.toDocument(subsidiary))
                .map(SubsidaryMapper::toDomain);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Subsidiary> update(Subsidiary subsidiary) {
        return save(subsidiary);
    }

    @Override
    public Flux<Subsidiary> findByFranchiseId(String franchiseId) {
        return repository.findByFranchiseId(franchiseId)
                .map(SubsidaryMapper::toDomain);
    }
}
