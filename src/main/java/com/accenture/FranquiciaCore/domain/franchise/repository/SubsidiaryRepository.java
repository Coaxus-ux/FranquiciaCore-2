package com.accenture.franquiciaCore.domain.franchise.repository;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubsidiaryRepository {
    Mono<Subsidiary> findById(String id);
    Mono<Subsidiary> save(Subsidiary subsidiary);
    Mono<Subsidiary> update(Subsidiary subsidiary);
    Flux<Subsidiary> findAll();
    Flux<Subsidiary> findByFranchiseId(String franchiseId);
}
