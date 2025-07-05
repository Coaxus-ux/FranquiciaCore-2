package com.accenture.franquiciaCore.domain.franchise.repository;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> findById(String id);
    Mono<Franchise> save(Franchise franchise);
    Flux<Franchise> findAll();
    Mono<Franchise> update(Franchise franchise);
}
