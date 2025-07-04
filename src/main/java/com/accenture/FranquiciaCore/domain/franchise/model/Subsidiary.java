package com.accenture.franquiciaCore.domain.franchise.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subsidiary {
    private String id;
    private String name;
    private String franchiseId;
}
