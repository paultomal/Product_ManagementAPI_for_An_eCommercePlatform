package com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.controller;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add_product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO) {
    ProductDTO productDTO1 = ProductDTO.from(productService.addProduct(productDTO));
    return ResponseEntity.ok(productDTO1);
    }
}
