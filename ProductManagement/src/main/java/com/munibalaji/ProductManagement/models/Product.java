package com.munibalaji.ProductManagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name cannot be null")
    private String productName;
    private String description;
    @Positive(message = "Price of a product must be greater than zero")
    private Double price;
    private Integer stockQuantity;

}
