package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;
import org.bson.types.ObjectId;

public class StockMapper {
  public static Stock toDomain(StockDocument doc) {
    return Stock.builder()
        .id(new StockId(doc.getId().toString()))
        .quantity(doc.getQuantity())
        .build()
        .withProductId(new ProductId(doc.getProductId().toString()));
  }

  public static StockDocument toDocument(Stock stock) {
    return StockDocument.builder()
        .id(new ObjectId(stock.getId().getValue()))
        .productId(new ObjectId(stock.getId().getValue()))
        .quantity(stock.getQuantity())
        .build();
  }
}
