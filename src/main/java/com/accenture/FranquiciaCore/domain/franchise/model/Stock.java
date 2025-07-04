package com.accenture.FranquiciaCore.domain.franchise.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
  private String id;
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
}
