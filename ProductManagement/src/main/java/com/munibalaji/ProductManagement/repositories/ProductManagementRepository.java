package com.munibalaji.ProductManagement.repositories;

import com.munibalaji.ProductManagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductManagementRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
