package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.application.controller;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.application.service.ProductService;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto.PaginatedProductResponse;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto.ProductDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto.UpdateStockDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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
    public ResponseEntity<PaginatedProductResponse> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Sort sort = Sort.by(sortBy).ascending();
        if (!List.of("id", "name", "price", "stockQuantity").contains(sortBy)) {
            sort = Sort.by("id").ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductDTO> productPage = productService.getAllProduct(pageable).map(ProductDTO::from);

        PaginatedProductResponse response = new PaginatedProductResponse();
        response.setPageNumber(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setProducts(productPage.getContent());

        return ResponseEntity.ok(response);
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

    @PatchMapping("/{id}/apply-discount")
    public ResponseEntity<?> applyDiscount(@PathVariable Long id, @RequestParam BigDecimal discount) {
        ProductDTO updatedProduct = productService.applyDiscount(id, discount);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
