package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product extends BaseEntity {
    @NotBlank(message = "Product name is required.")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Price is required.")
    @Min(value = 0, message = "Price must be positive.")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0, message = "Stock quantity must be non-negative.")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Embedded
    private Category category;

    @Min(value = 0, message = "Discount must be non-negative.")
    @Column(name = "discount", nullable = false)
    private BigDecimal discount = BigDecimal.ZERO; // percentage

    public BigDecimal calculateDiscountedPrice() {
        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discount.divide(BigDecimal.valueOf(100)));
        return price.multiply(discountMultiplier);
    }
}
