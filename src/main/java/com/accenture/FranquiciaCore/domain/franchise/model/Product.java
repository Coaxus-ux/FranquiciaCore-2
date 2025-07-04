package com.accenture.FranquiciaCore.domain.franchise.model;

import com.accenture.FranquiciaCore.domain.franchise.model.enums.CategoryProduct;

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
