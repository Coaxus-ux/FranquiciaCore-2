package com.accenture.franquiciaCore.infrastructure.persistence.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper.FranchiseMapper;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository.FranchiseMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoFranchiseRepository implements FranchiseRepository {

    private final FranchiseMongoRepository repository;

    @Override
    public Mono<Franchise> findById(String id) {
        return repository.findById(id)
                .map(FranchiseMapper::toDomain);
    }

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return repository.save(FranchiseMapper.toDocument(franchise))
                .map(FranchiseMapper::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() {
        return repository.findAll()
                .map(FranchiseMapper::toDomain);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Franchise> update(Franchise franchise) {
        return save(franchise);
    }
}
