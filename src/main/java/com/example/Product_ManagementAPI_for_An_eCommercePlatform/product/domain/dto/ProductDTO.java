package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.dto.BaseDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal discountedPrice;
    private Integer stockQuantity;
    private String Category_name;

    public static ProductDTO from(Product product) {
        if (product == null) return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setDiscountedPrice(product.calculateDiscountedPrice());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setCategory_name(product.getCategory().getCategory_name());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        return productDTO;
    }

}
