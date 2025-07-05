package com.accenture.franquiciaCore.infrastructure.rest.franchise.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseResponse;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.mapper.FranchiseRestMapper;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.controller.FranchiseController;

@Component
public class FranchiseModelAssembler
    implements RepresentationModelAssembler<Franchise, EntityModel<FranchiseResponse>> {

  private final FranchiseRestMapper mapper;

  public FranchiseModelAssembler(FranchiseRestMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public EntityModel<FranchiseResponse> toModel(Franchise entity) {
    FranchiseResponse dto = mapper.toResponse(entity);
    return EntityModel.of(dto)
        .add(linkTo(methodOn(FranchiseController.class)
                        .find(entity.getId().getValue()))
                .withSelfRel())
        .add(linkTo(methodOn(FranchiseController.class)
                        .create(null))
                .withRel("create"))
        .add(linkTo(methodOn(FranchiseController.class)
                        .update(entity.getId().getValue(), null))
                .withRel("update"));
  }
}
