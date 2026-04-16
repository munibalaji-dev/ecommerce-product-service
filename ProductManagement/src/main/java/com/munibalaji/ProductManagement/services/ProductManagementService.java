package com.munibalaji.ProductManagement.services;

import com.munibalaji.ProductManagement.dtos.ProductRequestDto;
import com.munibalaji.ProductManagement.dtos.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductManagementService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto);

    ProductResponseDto deleteProductById(Long id);

    Page<ProductResponseDto> searchProducts(String productName, Double minPrice, int page, int size, String sortBy, String direction);
}
