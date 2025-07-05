package com.accenture.franquiciaCore.infrastructure.rest.subsidiary.mapper;


import org.springframework.stereotype.Component;
import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.infrastructure.rest.subsidiary.dto.SubsidiaryResponse;

@Component
public class SubsidiaryRestMapper {
  public SubsidiaryResponse toResponse(Subsidiary sub) {
    return new SubsidiaryResponse(
      sub.getId().getValue(),
      sub.getName(),
      sub.getFranchiseId().getValue()
    );
  }
}
