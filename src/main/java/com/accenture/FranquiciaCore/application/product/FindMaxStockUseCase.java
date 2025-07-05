package com.accenture.franquiciaCore.application.product;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import reactor.core.publisher.Flux;

public interface FindMaxStockUseCase {
    Flux<Product> findMaxStockByFranchise(FindMaxStockCommand cmd);
}
