package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.dto.BaseDTO;
import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {
    @NotBlank(message = "Product name is required.")
    private String name;

    private String description;

    @NotNull(message = "Price is required.")
    @Min(value = 0, message = "Price must be positive.")
    private BigDecimal price;

    @Min(value = 0, message = "Discount must be non-negative.")
    private BigDecimal discount;

    private BigDecimal discountedPrice;

    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0, message = "Stock quantity must be non-negative.")
    private Integer stockQuantity;

    @NotBlank(message = "Category name is required.")
    private String categoryName;

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
        productDTO.setCategoryName(product.getCategory().getCategory_name());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        return productDTO;
    }

}
