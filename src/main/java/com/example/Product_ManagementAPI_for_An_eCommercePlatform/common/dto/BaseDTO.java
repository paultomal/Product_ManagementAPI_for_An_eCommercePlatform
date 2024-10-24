package com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO {
    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
