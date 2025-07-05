package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.controller.SubsidiaryController;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto.SubsidiaryResponse;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.mapper.SubsidiaryRestMapper;

@Component
public class SubsidiaryModelAssembler
    implements RepresentationModelAssembler<Subsidiary, EntityModel<SubsidiaryResponse>> {

  private final SubsidiaryRestMapper mapper;

  public SubsidiaryModelAssembler(SubsidiaryRestMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public EntityModel<SubsidiaryResponse> toModel(Subsidiary entity) {
    SubsidiaryResponse dto = mapper.toResponse(entity);
    return EntityModel.of(dto)
        .add(linkTo(methodOn(SubsidiaryController.class)
                        .create(null))
                .withRel("create"));
  }
}
