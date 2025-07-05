package com.accenture.franquiciaCore.domain.franchise.repository;

import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;

import reactor.core.publisher.Mono;

public interface StockRepository {
    Mono<Stock> findByProductId(ProductId productId);
    Mono<Stock> save(Stock stock);
    Mono<Stock> update(Stock stock);
    Mono<Void> delete(String id);
    Mono<Void> deleteByProductId(ProductId productId);
}
