package com.accenture.franquiciaCore.application.subsidiary;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;

import reactor.core.publisher.Mono;

public interface UpdateSubsidiaryUseCase {
  Mono<Subsidiary> update(UpdateSubsidiaryCommand cmd);
}
