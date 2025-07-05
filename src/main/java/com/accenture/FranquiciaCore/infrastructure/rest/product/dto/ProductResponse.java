package com.accenture.franquiciaCore.infrastructure.rest.product.dto;


import lombok.Data;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String category;
    private int stock;
    private String subsidiaryId;
    private String subsidiaryName;
}
