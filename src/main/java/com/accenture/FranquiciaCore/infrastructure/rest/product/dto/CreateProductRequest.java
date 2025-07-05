package com.accenture.franquiciaCore.infrastructure.rest.product.dto;

import lombok.Data;

@Data
public class CreateProductRequest {
  private String name;
  private String category;
  private int quantity; 
}
