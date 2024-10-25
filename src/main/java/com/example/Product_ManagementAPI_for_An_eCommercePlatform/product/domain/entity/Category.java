package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Category {
    private String category_name;
}
