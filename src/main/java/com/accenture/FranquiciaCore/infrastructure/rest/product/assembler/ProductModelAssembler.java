package com.accenture.franquiciaCore.infrastructure.rest.product.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.infrastructure.rest.product.controller.ProductController;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.ProductResponse;
import com.accenture.franquiciaCore.infrastructure.rest.product.mapper.ProductRestMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductModelAssembler
    implements RepresentationModelAssembler<Product, EntityModel<ProductResponse>> {

  private final ProductRestMapper mapper;

  @Override
  public EntityModel<ProductResponse> toModel(Product product) {
    
    ProductResponse dto = mapper.toResponse(product);

    return EntityModel.of(dto,
      linkTo(methodOn(ProductController.class)
          .create(product.getSubsidiaryId().getValue(), null))
        .withSelfRel(),
      linkTo(methodOn(ProductController.class)
          .updateStock(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue(),
             null))
        .withRel("updateStock"),
      linkTo(methodOn(ProductController.class)
          .updateName(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue(),
             null))
        .withRel("updateName"),
      linkTo(methodOn(ProductController.class)
          .delete(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue()))
        .withRel("delete")
    );
  }
}
