package com.munibalaji.ProductManagement.repositories.specifications;

import com.munibalaji.ProductManagement.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasMinPrice(Double price){
        return (root, query, criteriaBuilder) ->
                price == null ? null : criteriaBuilder.greaterThan(root.get("price"), price);

    }

    public static Specification<Product> hasProductName(String name){
//        return (root, query, criteriaBuilder) ->
//                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+ name.toLowerCase()+ "%");

        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty())
                return null;

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%"+ name.toLowerCase()+ "%");
        };
    }
}
