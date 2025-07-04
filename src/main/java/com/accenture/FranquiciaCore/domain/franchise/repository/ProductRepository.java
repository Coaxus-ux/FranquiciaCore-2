package com.accenture.franquiciaCore.domain.franchise.repository;

import com.accenture.franquiciaCore.domain.franchise.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> findById(String id);
    Flux<Product> findBySubsidiaryId(String subsidiaryId);
    Mono<Product> save(Product product);
    Mono<Void> delete(String id);
    Mono<Product> update(Product product);
}
