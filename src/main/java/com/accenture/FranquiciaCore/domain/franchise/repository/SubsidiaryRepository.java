package com.accenture.FranquiciaCore.domain.franchise.repository;

import com.accenture.FranquiciaCore.domain.franchise.model.Subsidiary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubsidiaryRepository {
    Mono<Subsidiary> findById(String id);
    Flux<Subsidiary> findByFranchiseId(String franchiseId);
    Mono<Subsidiary> save(Subsidiary subsidiary);
    Mono<Void> delete(String id);
    Mono<Subsidiary> update(Subsidiary subsidiary);
}
