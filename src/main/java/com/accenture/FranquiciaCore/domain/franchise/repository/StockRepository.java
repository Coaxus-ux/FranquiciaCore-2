package com.accenture.FranquiciaCore.domain.franchise.repository;

import com.accenture.FranquiciaCore.domain.franchise.model.Stock;
import reactor.core.publisher.Mono;

public interface StockRepository {
    Mono<Stock> findByProductId(String productId);
    Mono<Stock> save(Stock stock);
    Mono<Stock> update(Stock stock);
    Mono<Void> delete(String id);
}
