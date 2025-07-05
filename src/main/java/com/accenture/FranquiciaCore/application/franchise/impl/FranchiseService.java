package com.accenture.franquiciaCore.application.franchise.impl;

import com.accenture.franquiciaCore.application.franchise.CreateFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseUseCase; 
import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.shared.util.IdGenerator;
import com.accenture.franquiciaCore.application.franchise.CreateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindAllFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindTopProductsUseCase;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseTopProductsResponse;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.SubsidiaryTopProduct;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.TopProductDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FranchiseService implements CreateFranchiseUseCase, FindFranchiseUseCase, UpdateFranchiseUseCase, FindAllFranchiseUseCase, FindTopProductsUseCase {
  private final FranchiseRepository franchiseRepository;
  private final SubsidiaryRepository subsidiaryRepository;
  private final ProductRepository productRepository;
  private final StockRepository stockRepository;

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
    return franchiseRepository.findById(cmd.getId())
        .switchIfEmpty(Mono.error(new NoSuchElementException("Franchise not found " + cmd.getId())));
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

  @Override
  public Flux<Franchise> findAll() {
    return franchiseRepository.findAll();
  }

  @Override
  public Flux<FranchiseTopProductsResponse> findAllTopProducts() {
    return franchiseRepository.findAll()
      .flatMap(franchise ->
        subsidiaryRepository.findByFranchiseId(franchise.getId().getValue())
          .flatMap(sub ->
            productRepository.findBySubsidiaryId(sub.getId().getValue())
              .flatMap(prod ->
                stockRepository.findByProductId(prod.getId())
                  .map(stock -> prod.withStock(stock))
              )
              .sort((a, b) -> Integer.compare(b.getStock().getQuantity(), a.getStock().getQuantity()))
              .next()
              .map(top -> new SubsidiaryTopProduct(
                  sub.getId().getValue(),
                  sub.getName(),
                  new TopProductDto(
                    top.getId().getValue(),
                    top.getName(),
                    top.getStock().getQuantity()
                  )
              ))
          )
          .collectList()
          .map(list -> new FranchiseTopProductsResponse(
            franchise.getId().getValue(),
            franchise.getName(),
            list
          ))
      );
  }
}
