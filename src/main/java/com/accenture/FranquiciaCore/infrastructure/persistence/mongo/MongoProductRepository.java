package com.accenture.franquiciaCore.infrastructure.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper.ProductMapper;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository.ProductMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MongoProductRepository implements ProductRepository {

    private final ProductMongoRepository repository;

    @Override
    public Mono<Product> findById(String id) {
        return repository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(ProductMapper.toDocument(product))
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Product> update(Product product) {
        return save(product);
    }


    @Override
    public Flux<Product> findBySubsidiaryId(String subsidiaryId) {
        return repository.findBySubsidiaryId(subsidiaryId)
                .map(ProductMapper::toDomain);
    }

}
