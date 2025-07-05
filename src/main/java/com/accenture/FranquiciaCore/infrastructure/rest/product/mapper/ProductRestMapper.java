package com.accenture.franquiciaCore.infrastructure.rest.product.mapper;

import org.springframework.stereotype.Component;
import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.ProductResponse;

@Component
public class ProductRestMapper {
  public ProductResponse toResponse(Product p) {
    ProductResponse dto = new ProductResponse();
    dto.setId(p.getId().getValue());
    dto.setName(p.getName());
    dto.setCategory(p.getCategory().name());
    dto.setStock(p.getStock().getQuantity());
    dto.setSubsidiaryId(p.getSubsidiaryId().getValue());
    return dto;
  }

  public ProductResponse toResponse(Product p, String subsidiaryName) {
    ProductResponse dto = toResponse(p);
    dto.setSubsidiaryName(subsidiaryName);
    return dto;
  }
}
