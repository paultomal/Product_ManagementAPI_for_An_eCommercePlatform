package com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.service;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.entity.Product;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;


    public Product addProduct(@Valid ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStockQuantity(productDTO.getStockQuantity());
        productRepository.save(product);

        log.info("Product is created. Name: {}", product.getName());
        return product;
    }
}
