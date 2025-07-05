package com.accenture.franquiciaCore.application.subsidiary;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.application.subsidiary.FindSubsidiaryCommand;
import reactor.core.publisher.Mono;

public interface FindSubsidiaryUseCase {
  Mono<Subsidiary> findById(FindSubsidiaryCommand cmd);
}
