package com.accenture.franquiciaCore.application.franchise.impl;

import com.accenture.franquiciaCore.application.franchise.CreateFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseUseCase;
import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.shared.util.IdGenerator;
import com.accenture.franquiciaCore.application.franchise.CreateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService implements CreateFranchiseUseCase, FindFranchiseUseCase, UpdateFranchiseUseCase {
  private final FranchiseRepository franchiseRepository;

  @Override
  public Mono<Franchise> createFranchise(CreateFranchiseCommand cmd) {
    Franchise franchise = Franchise.builder()
        .id(new FranchiseId(IdGenerator.generate()))
        .name(cmd.getName())
        .build();

    return franchiseRepository.save(franchise);
  }

  @Override
  public Mono<Franchise> findFranchise(FindFranchiseCommand cmd) {
    return franchiseRepository.findById(cmd.getId());
  }

  @Override
  public Mono<Franchise> updateFranchise(UpdateFranchiseCommand cmd) {
    return franchiseRepository.findById(cmd.getId())
        .flatMap(franchise -> {
          Franchise updatedFranchise = Franchise.builder()
              .id(franchise.getId())
              .name(cmd.getName())
              .build();
          return franchiseRepository.save(updatedFranchise);
        });
  }
}
