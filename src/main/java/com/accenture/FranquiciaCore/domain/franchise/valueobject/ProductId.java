package com.accenture.franquiciaCore.domain.franchise.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class ProductId {
  private final String value;

  public ProductId(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("ProductId cannot be empty");
    }
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
