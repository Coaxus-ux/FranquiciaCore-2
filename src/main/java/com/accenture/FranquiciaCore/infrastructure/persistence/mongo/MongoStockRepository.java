package com.accenture.FranquiciaCore.infrastructure.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.accenture.FranquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.mapper.StockMapper;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.repository.StockMongoRepository;
import reactor.core.publisher.Mono;
import com.accenture.FranquiciaCore.domain.franchise.model.Stock;
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
