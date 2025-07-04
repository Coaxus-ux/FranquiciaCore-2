package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private String id;
  private String name;
  private CategoryProduct category;
  private Stock stock;

}
