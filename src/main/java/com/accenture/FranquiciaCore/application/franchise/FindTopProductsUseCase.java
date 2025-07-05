package com.accenture.franquiciaCore.application.franchise;


import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseTopProductsResponse;

import reactor.core.publisher.Flux;

public interface FindTopProductsUseCase {
  Flux<FranchiseTopProductsResponse> findAllTopProducts();
}
