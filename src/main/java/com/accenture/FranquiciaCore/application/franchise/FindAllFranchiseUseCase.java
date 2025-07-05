package com.accenture.franquiciaCore.application.franchise;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;

import reactor.core.publisher.Flux;

public interface FindAllFranchiseUseCase {
  Flux<Franchise> findAll();
}
