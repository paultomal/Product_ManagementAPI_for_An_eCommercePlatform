package com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.controller;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.dto.UpdateStockDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.entity.Product;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.domain_layer.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProduct(pageable);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.getProductById(id);
        if (product != null) {
            ProductDTO productDTO1 = ProductDTO.from(productService.updateProduct(id, productDTO));
            return new ResponseEntity<>(productDTO1, HttpStatus.OK);
        } else return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            Boolean deleted = productService.deleteProduct(id);
            if (deleted) {
                return new ResponseEntity<>("Product " + id + " is deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/update-stock")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockDTO updateStockDTO) {
        ProductDTO updatedProduct = productService.updateStock(id, updateStockDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
