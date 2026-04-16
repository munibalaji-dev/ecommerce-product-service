package com.munibalaji.ProductManagement.controllers;

import com.munibalaji.ProductManagement.dtos.ProductRequestDto;
import com.munibalaji.ProductManagement.dtos.ProductResponseDto;
import com.munibalaji.ProductManagement.services.ProductManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/products")
@Tag(name = "Product Service ", description = "Operations related to products")
public class ProductController {

    private ProductManagementService productManagementService;

    @Autowired
    public ProductController(ProductManagementService productManagementService){
        this.productManagementService = productManagementService;
    }
    @PostMapping
    @Operation(description = "Creates a product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productManagementService.createProduct(productRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get a product by id")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productManagementService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Get all the products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return new ResponseEntity<>(productManagementService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update product using product id")
    public ResponseEntity<ProductResponseDto> updateProductById(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productManagementService.updateProductById(id, productRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a product using product id")
    public ResponseEntity<ProductResponseDto> deleteProductById(@PathVariable Long id){
        return new ResponseEntity<>(productManagementService.deleteProductById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchProducts(@RequestParam(required = false) String productName,
                                                                   @RequestParam(required = false) Double minPrice,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "5") int size,
                                                                   @RequestParam(defaultValue = "id") String sortBy,
                                                                   @RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.ok(
                productManagementService.searchProducts(productName, minPrice, page, size, sortBy, direction));
    }
}
