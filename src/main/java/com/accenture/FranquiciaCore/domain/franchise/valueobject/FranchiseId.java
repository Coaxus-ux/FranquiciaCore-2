package com.accenture.franquiciaCore.domain.franchise.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class FranchiseId {
    private final String value;

    public FranchiseId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("FranchiseId cannot be empty");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
