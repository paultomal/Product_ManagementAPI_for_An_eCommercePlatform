package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.repository;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
