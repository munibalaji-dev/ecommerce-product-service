package com.munibalaji.ProductManagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String productName;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
