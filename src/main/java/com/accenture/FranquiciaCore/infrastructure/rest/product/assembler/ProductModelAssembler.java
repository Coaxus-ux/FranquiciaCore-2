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
    // 1) convierto al DTO
    ProductResponse dto = mapper.toResponse(product);

    // 2) construyo links HATEOAS
    return EntityModel.of(dto,
      // Self: GET /api/subsidiaries/{subsidiaryId}/products/{productId}
      linkTo(methodOn(ProductController.class)
          .create(product.getSubsidiaryId().getValue(), null))
        .withSelfRel(),
      // Update stock
      linkTo(methodOn(ProductController.class)
          .updateStock(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue(),
             null))
        .withRel("updateStock"),
      // Update name
      linkTo(methodOn(ProductController.class)
          .updateName(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue(),
             null))
        .withRel("updateName"),
      // Delete
      linkTo(methodOn(ProductController.class)
          .delete(
             product.getSubsidiaryId().getValue(),
             product.getId().getValue()))
        .withRel("delete")
    );
  }
}
