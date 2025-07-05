package com.accenture.franquiciaCore.application.subsidiary.impl;

import java.util.NoSuchElementException;

import com.accenture.franquiciaCore.domain.shared.util.IdGenerator;
import com.accenture.franquiciaCore.application.subsidiary.AddSubsidiaryCommand;
import com.accenture.franquiciaCore.application.subsidiary.AddSubsidiaryUseCase;
import com.accenture.franquiciaCore.application.subsidiary.FindAllSubsidiaryUseCase;
import com.accenture.franquiciaCore.application.subsidiary.FindSubsidiaryCommand;
import com.accenture.franquiciaCore.application.subsidiary.FindSubsidiaryUseCase;
import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class SubsidiaryService implements AddSubsidiaryUseCase, FindAllSubsidiaryUseCase, FindSubsidiaryUseCase {
  private final SubsidiaryRepository subsidiaryRepository;
  private final FranchiseRepository  franchiseRepository;  

  @Override
  public Mono<Subsidiary> addSubsidiary(AddSubsidiaryCommand cmd) {
    return franchiseRepository.findById(cmd.getFranchiseId())
      .switchIfEmpty(Mono.error(new NoSuchElementException("Franchise not found " + cmd.getFranchiseId())))
      .flatMap(franchise -> {
        var sub = Subsidiary.builder()
            .id(new SubsidiaryId(IdGenerator.generate()))
            .name(cmd.getName())
            .franchiseId(franchise.getId())
            .build();
        franchise.addSubsidiary(sub);
        return subsidiaryRepository.save(sub);
      });
  }

  @Override
  public Flux<Subsidiary> findAll() {
    return subsidiaryRepository.findAll();
  }

  @Override
  public Mono<Subsidiary> findById(FindSubsidiaryCommand cmd) {
    return subsidiaryRepository.findById(cmd.getId())
      .switchIfEmpty(Mono.error(new NoSuchElementException("Subsidiary not found " + cmd.getId())));
  }
}
