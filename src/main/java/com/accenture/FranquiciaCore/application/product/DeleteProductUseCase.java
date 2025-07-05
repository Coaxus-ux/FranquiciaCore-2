package com.accenture.franquiciaCore.application.product;


import reactor.core.publisher.Mono;

public interface DeleteProductUseCase {
    Mono<Void> deleteProduct(DeleteProductCommand cmd);
}
