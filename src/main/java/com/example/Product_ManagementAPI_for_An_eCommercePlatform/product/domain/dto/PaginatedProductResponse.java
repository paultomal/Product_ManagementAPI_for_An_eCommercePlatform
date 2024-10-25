package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedProductResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private List<ProductDTO> products;
}
