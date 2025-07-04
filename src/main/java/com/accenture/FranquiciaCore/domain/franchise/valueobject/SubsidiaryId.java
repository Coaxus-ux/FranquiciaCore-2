package com.accenture.franquiciaCore.domain.franchise.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class SubsidiaryId {
  private final String value;

  public SubsidiaryId(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("SubsidiaryId cannot be empty");
    }
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
