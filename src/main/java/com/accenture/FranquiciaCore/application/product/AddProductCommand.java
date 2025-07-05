package com.accenture.franquiciaCore.application.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductCommand {
  private String name;
  private String subsidiaryId;
  private String category;
  private int quantity;
}
