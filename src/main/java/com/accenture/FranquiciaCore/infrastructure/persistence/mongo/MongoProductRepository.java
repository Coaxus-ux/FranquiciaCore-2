package com.accenture.FranquiciaCore.infrastructure.persistence.mongo;

import com.accenture.FranquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.mapper.ProductMapper;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository.ProductMongoRepository;
import com.accenture.FranquiciaCore.domain.franchise.model.Product;


import org.springframework.stereotype.Repository;
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
    public Flux<Product> findByFranchiseId(String franchiseId) {
        return repository.findByFranchiseId(franchiseId)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Flux<Product> findBySubsidiaryId(String subsidiaryId) {
        return repository.findBySubsidiaryId(subsidiaryId)
                .map(ProductMapper::toDomain);
    }

}
