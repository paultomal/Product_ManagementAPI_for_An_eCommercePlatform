package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.application.service;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto.UpdateStockDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService implements Serializable {
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

    public Page<Product> getAllProduct(Pageable pageable) {
        log.info("All Products Founded");
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> log.info("Product is Found. Product Name: {}", value.getName()));
        return product.orElse(null);
    }

    public Product updateProduct(Long id, @Valid ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            product.get().setName(productDTO.getName());
            product.get().setDescription(productDTO.getDescription());
            product.get().setPrice(productDTO.getPrice());
            product.get().setCategory(productDTO.getCategory());
            productRepository.save(product.get());
            log.info("Product is updated. Name: {}", productDTO.getName());
            return product.get();
        }
        return null;
    }

    public Boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            log.info("Product is deleted.");
            return true;
        }
        return false;
    }

    public ProductDTO updateStock(Long id, UpdateStockDTO updateStockDTO) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStockQuantity(updateStockDTO.getStockQuantity());
            productRepository.save(product);
            log.info("Stock is updated. Name of The Product: {}", product.getName());
            return ProductDTO.from(product);
        }
        return null;
    }
    public ProductDTO applyDiscount(Long id, BigDecimal discount) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setDiscount(discount);
            productRepository.save(product);
            return ProductDTO.from(product);
        }
        return null;
    }
}
