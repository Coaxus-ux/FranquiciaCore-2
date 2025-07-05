package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.franquiciaCore.application.subsidiary.AddSubsidiaryCommand;
import com.accenture.franquiciaCore.application.subsidiary.AddSubsidiaryUseCase;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.assembler.SubsidiaryModelAssembler;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto.CreateSubsidiaryRequest;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto.SubsidiaryResponse;
import com.accenture.franquiciaCore.application.subsidiary.FindAllSubsidiaryUseCase;
import com.accenture.franquiciaCore.application.subsidiary.FindSubsidiaryCommand;
import com.accenture.franquiciaCore.application.subsidiary.FindSubsidiaryUseCase;
import com.accenture.franquiciaCore.application.subsidiary.UpdateSubsidiaryUseCase;
import com.accenture.franquiciaCore.application.subsidiary.UpdateSubsidiaryCommand;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subsidiary")
@RequiredArgsConstructor
public class SubsidiaryController {
  private final AddSubsidiaryUseCase addSubsidiaryUseCase;
  private final SubsidiaryModelAssembler assembler;
  private final FindAllSubsidiaryUseCase findAllSubsidiaryUseCase;
  private final FindSubsidiaryUseCase findSubsidiaryUseCase;
  private final UpdateSubsidiaryUseCase updateSubsidiaryUseCase;
  @PostMapping
  public Mono<ResponseEntity<EntityModel<SubsidiaryResponse>>> create(@Valid @RequestBody CreateSubsidiaryRequest request) {
    AddSubsidiaryCommand cmd = new AddSubsidiaryCommand(request.getFranchiseId(), request.getName());
    return addSubsidiaryUseCase.addSubsidiary(cmd)
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
  }

  @GetMapping("/all")
  public Flux<EntityModel<SubsidiaryResponse>> findAll() {
    return findAllSubsidiaryUseCase.findAll()
        .map(assembler::toModel);
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<EntityModel<SubsidiaryResponse>>> findById(@PathVariable String id) {
    return findSubsidiaryUseCase.findById(new FindSubsidiaryCommand(id))
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
                
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<EntityModel<SubsidiaryResponse>>> update(@PathVariable String id, @RequestBody CreateSubsidiaryRequest request) {
    UpdateSubsidiaryCommand cmd = new UpdateSubsidiaryCommand(id, request.getName(), request.getFranchiseId());
    return updateSubsidiaryUseCase.update(cmd)
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
  }
}
