package com.accenture.franquiciaCore.application.subsidiary;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;

import reactor.core.publisher.Flux;

public interface FindAllSubsidiaryUseCase {
  Flux<Subsidiary> findAll();
}
