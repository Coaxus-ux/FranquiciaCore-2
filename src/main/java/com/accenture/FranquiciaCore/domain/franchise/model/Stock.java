package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
public class Stock {
  private StockId id;
  private int quantity;

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  public void removeQuantity(int quantity) {
    if (this.quantity < quantity) {
      throw new IllegalArgumentException("Quantity cannot be greater than the stock");
    }
    this.quantity -= quantity;
  }
  public Stock withProductId(ProductId productId) {
    return this.toBuilder()
               .id(new StockId(productId.getValue()))
               .build();
  }
}
