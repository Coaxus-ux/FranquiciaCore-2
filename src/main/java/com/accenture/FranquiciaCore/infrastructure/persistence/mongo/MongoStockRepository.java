package com.accenture.franquiciaCore.infrastructure.persistence.mongo;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper.StockMapper;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.repository.StockMongoRepository;
import java.util.NoSuchElementException;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MongoStockRepository implements StockRepository {

  private final StockMongoRepository repository;

  @Override
  public Mono<Stock> findByProductId(ProductId productId) {
    ObjectId oid = new ObjectId(productId.getValue());
    return repository.findByProductId(oid)
        .switchIfEmpty(Mono.error(
           new NoSuchElementException("Stock not found for product " + productId.getValue())))
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
    return repository.deleteById(new ObjectId(id));
  }

  @Override
  public Mono<Void> deleteByProductId(ProductId productId) {
    ObjectId oid = new ObjectId(productId.getValue());
    return repository.deleteById(oid);
  }
}
