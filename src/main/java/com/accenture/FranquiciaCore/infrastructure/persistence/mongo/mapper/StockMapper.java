package com.accenture.franquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.infrastructure.persistence.mongo.model.StockDocument;

public class StockMapper {
  public static Stock toDomain(StockDocument doc) {
    return Stock.builder()
        .id(new StockId(doc.getId()))
        .quantity(doc.getQuantity())
        .build();
  }

  public static StockDocument toDocument(Stock stock) {
    return StockDocument.builder()
        .id(stock.getId().toString())
        .quantity(stock.getQuantity())
        .build();
  }
}
