package com.munibalaji.ProductManagement.services;

import com.munibalaji.ProductManagement.dtos.ProductRequestDto;
import com.munibalaji.ProductManagement.dtos.ProductResponseDto;
import com.munibalaji.ProductManagement.exceptions.ResourceNotFoundException;
import com.munibalaji.ProductManagement.mappers.ProductMapper;
import com.munibalaji.ProductManagement.models.Product;
import com.munibalaji.ProductManagement.repositories.ProductManagementRepository;
import com.munibalaji.ProductManagement.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductManagementService{

    private ProductManagementRepository productManagementRepository;

    @Autowired
    public ProductServiceImplementation(ProductManagementRepository productManagementRepository){
        this.productManagementRepository = productManagementRepository;
    }
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product pro = ProductMapper.requestDtoToProduct(productRequestDto);

        Product saved = productManagementRepository.save(pro);

        return ProductMapper.entityToProductResponseDto(saved);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        Product getById = productManagementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with your id try again"));

        return ProductMapper.entityToProductResponseDto(getById);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        List<Product> products = productManagementRepository.findAll();


        return products.stream()
                .map(ProductMapper::entityToProductResponseDto)
                .toList();
    }


    @Override
    public ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto) {

        Product product = productManagementRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not found with your id to update the product"));


        product.setProductName(productRequestDto.getProductName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStockQuantity(productRequestDto.getStockQuantity());

        Product updatedProduct = productManagementRepository.save(product);
        return ProductMapper.entityToProductResponseDto(updatedProduct);
    }

    @Override
    public ProductResponseDto deleteProductById(Long id) {

        Product product = productManagementRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with your id to delete a product"));

        productManagementRepository.delete(product);

        return null;
    }

    @Override
    public Page<ProductResponseDto> searchProducts(String productName, Double minPrice, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (productName != null){
            specification = specification.and(ProductSpecification.hasProductName(productName));
        }
        if (minPrice != null){
            specification = specification.and(ProductSpecification.hasMinPrice(minPrice));
        }

        return productManagementRepository.findAll(specification, pageable)
                .map(ProductMapper::entityToProductResponseDto);

    }
}
