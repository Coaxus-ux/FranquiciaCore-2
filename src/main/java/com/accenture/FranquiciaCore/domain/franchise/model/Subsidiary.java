package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Builder
public class Subsidiary {
    private SubsidiaryId id;
    private String name;
    private FranchiseId franchiseId;
    
    public Subsidiary(SubsidiaryId id, String name, FranchiseId franchiseId) {
        this.id = id;
        this.name = validateName(name);
        this.franchiseId = franchiseId;
    }
    
    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subsidiary name cannot be empty");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Subsidiary name must be at least 3 characters long");
        }
        if (name.trim().length() > 255) {
            throw new IllegalArgumentException("Subsidiary name cannot exceed 255 characters");
        }
        return name.trim();
    }
}
