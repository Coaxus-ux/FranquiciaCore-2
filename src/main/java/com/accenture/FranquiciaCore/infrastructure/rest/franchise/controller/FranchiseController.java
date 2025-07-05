package com.accenture.franquiciaCore.infrastructure.rest.franchise.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.franquiciaCore.application.franchise.CreateFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.CreateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindAllFranchiseUseCase;
import com.accenture.franquiciaCore.application.franchise.FindTopProductsUseCase;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.CreateFranchiseRequest;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseResponse;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseTopProductsResponse;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.assembler.FranchiseModelAssembler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {
  private final CreateFranchiseUseCase createFranchiseUseCase;
  private final FindFranchiseUseCase findFranchiseUseCase;
  private final UpdateFranchiseUseCase updateFranchiseUseCase;
  private final FindAllFranchiseUseCase findAllFranchiseUseCase;
  private final FindTopProductsUseCase findTopProductsUseCase;
  private final FranchiseModelAssembler assembler;

  @PostMapping
  public Mono<ResponseEntity<EntityModel<FranchiseResponse>>> create(
      @Valid @RequestBody CreateFranchiseRequest request) {
    CreateFranchiseCommand cmd = new CreateFranchiseCommand(request.getName());
    return createFranchiseUseCase.createFranchise(cmd)
        .map(franchise -> {
          EntityModel<FranchiseResponse> model = assembler.toModel(franchise);
          String selfHref = model.getRequiredLink("self").getHref();
          return ResponseEntity
              .created(URI.create(selfHref))
              .body(model);
        })
        .onErrorResume(ConstraintViolationException.class, e -> Mono.just(ResponseEntity.badRequest().build()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<EntityModel<FranchiseResponse>>> find(@PathVariable String id) {
    FindFranchiseCommand cmd = new FindFranchiseCommand(id);
    return findFranchiseUseCase.findFranchise(cmd)
        .map(franchise -> {
          EntityModel<FranchiseResponse> model = assembler.toModel(franchise);
          return ResponseEntity.ok(model);
        })
        .onErrorResume(ConstraintViolationException.class, e -> Mono.just(ResponseEntity.badRequest().build()));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<EntityModel<FranchiseResponse>>> update(
      @PathVariable String id,
      @Valid @RequestBody CreateFranchiseRequest req) {
    UpdateFranchiseCommand cmd = new UpdateFranchiseCommand(id, req.getName());
    return updateFranchiseUseCase.updateFranchise(cmd)
        .map(entity -> assembler.toModel(entity))
        .map(ResponseEntity::ok)
        .onErrorResume(ConstraintViolationException.class,
            e -> Mono.just(ResponseEntity.badRequest().build()));
  }

  @GetMapping("/all")
  public Mono<ResponseEntity<List<EntityModel<FranchiseResponse>>>> findAll() {
    return findAllFranchiseUseCase.findAll()
        .map(assembler::toModel)
        .collectList()
        .map(ResponseEntity::ok);
  }
  
  @GetMapping("/top")
  public Flux<FranchiseTopProductsResponse> topAll() {
    return findTopProductsUseCase.findAllTopProducts();
  }
}
