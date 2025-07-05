package com.accenture.franquiciaCore.application.franchise.impl;

import com.accenture.franquiciaCore.application.franchise.CreateFranchiseUseCase;
import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.shared.util.IdGenerator;
import com.accenture.franquiciaCore.application.franchise.CreateFranchiseCommand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService implements CreateFranchiseUseCase {
  private final FranchiseRepository franchiseRepository;

  @Override
  public Mono<Franchise> createFranchise(CreateFranchiseCommand cmd) {
    Franchise franchise = Franchise.builder()
        .id(new FranchiseId(IdGenerator.generate()))
        .name(cmd.getName())
        .build();

    return franchiseRepository.save(franchise);
  }
}
