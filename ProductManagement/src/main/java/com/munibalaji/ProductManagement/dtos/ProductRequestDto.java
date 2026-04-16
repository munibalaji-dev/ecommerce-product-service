package com.munibalaji.ProductManagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    private String productName;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
