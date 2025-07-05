package com.accenture.franquiciaCore.infrastructure.rest.franchise.mapper;

import org.springframework.stereotype.Component;

import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseResponse;

@Component
public class FranchiseRestMapper {
    public FranchiseResponse toResponse(Franchise franchise) {
        return new FranchiseResponse(franchise.getId().getValue(), franchise.getName());
    }
} 
