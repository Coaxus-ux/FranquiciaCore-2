package com.accenture.franquiciaCore.application.franchise;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;

import reactor.core.publisher.Mono;

public interface UpdateFranchiseUseCase {
  Mono<Franchise> updateFranchise(UpdateFranchiseCommand cmd);
}
