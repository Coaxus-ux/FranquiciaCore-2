package com.accenture.franquiciaCore.infrastructure.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper.StockMapper;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository.StockMongoRepository;

import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MongoStockRepository implements StockRepository {

  private final StockMongoRepository repository;

  @Override
  public Mono<Stock> findByProductId(String productId) {
    return repository.findByProductId(productId)
        .map(StockMapper::toDomain);
  }

  @Override
  public Mono<Stock> save(Stock stock) {
    return repository.save(StockMapper.toDocument(stock))
        .map(StockMapper::toDomain);
  }

  @Override
  public Mono<Stock> update(Stock stock) {
    return save(stock);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repository.deleteById(id);
  }
}
