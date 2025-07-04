package com.accenture.franquiciaCore.domain.franchise.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class StockId {
  private final String value;

  public StockId(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("StockId cannot be empty");
    }
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
