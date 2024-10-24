package com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.controller;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.entity.Product;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductDTO.from(product));
    }
}
