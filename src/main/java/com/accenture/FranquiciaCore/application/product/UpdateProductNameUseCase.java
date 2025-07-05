package com.accenture.franquiciaCore.application.product;

import com.accenture.franquiciaCore.domain.franchise.model.Product;
import reactor.core.publisher.Mono;

public interface UpdateProductNameUseCase {
    Mono<Product> updateName(UpdateProductNameCommand cmd);
}
