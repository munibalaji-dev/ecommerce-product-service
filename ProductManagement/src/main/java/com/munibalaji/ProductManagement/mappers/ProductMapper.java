package com.munibalaji.ProductManagement.mappers;

import com.munibalaji.ProductManagement.dtos.ProductRequestDto;
import com.munibalaji.ProductManagement.dtos.ProductResponseDto;
import com.munibalaji.ProductManagement.models.Product;

public class ProductMapper {

    public static Product requestDtoToProduct(ProductRequestDto productRequestDto){
        if (productRequestDto == null){
            return null;
        }

        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStockQuantity(productRequestDto.getStockQuantity());

        return product;
    }

    public static ProductResponseDto entityToProductResponseDto(Product product){
        if (product == null){
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStockQuantity(product.getStockQuantity());

        return productResponseDto;
    }
}
