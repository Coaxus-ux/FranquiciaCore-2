package com.accenture.FranquiciaCore.infrastructure.persistence.mongo.mapper;

import com.accenture.FranquiciaCore.domain.franchise.model.Stock;
import com.accenture.FranquiciaCore.infrastructure.persistence.mongo.model.StockDocument;

public class StockMapper {
  public static Stock toDomain(StockDocument doc) {
    return Stock.builder()
        .id(doc.getId())
        .quantity(doc.getQuantity())
        .build();
  }

  public static StockDocument toDocument(Stock stock) {
    return StockDocument.builder()
        .id(stock.getId())
        .quantity(stock.getQuantity())
        .build();
  }
}
