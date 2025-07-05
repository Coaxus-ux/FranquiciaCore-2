package com.accenture.franquiciaCore.application.subsidiary;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import reactor.core.publisher.Mono;

public interface AddSubsidiaryUseCase {
    Mono<Subsidiary> addSubsidiary(AddSubsidiaryCommand cmd);
}
