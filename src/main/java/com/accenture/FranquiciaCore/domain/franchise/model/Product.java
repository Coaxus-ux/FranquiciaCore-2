package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
public class Product {
  private ProductId id;
  private String name;
  private CategoryProduct category;
  private Stock stock;
  private SubsidiaryId subsidiaryId;

  public Product(ProductId id, String name, CategoryProduct category, Stock stock, SubsidiaryId subsidiaryId) {
    this.id = id;
    this.name = validateName(name);
    this.category = category;
    this.stock = stock;
    this.subsidiaryId = subsidiaryId;
  }

  private String validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Product name cannot be empty");
    }
    if (name.trim().length() < 3) {
      throw new IllegalArgumentException("Product name must be at least 3 characters long");
    }
    if (name.trim().length() > 255) {
      throw new IllegalArgumentException("Product name cannot exceed 255 characters");
    }
    return name.trim();
  }

  public Product withStock(Stock stock) {
    return this.toBuilder()
               .stock(stock)
               .build();
  }
}
