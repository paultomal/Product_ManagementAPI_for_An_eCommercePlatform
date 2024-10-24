package com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Component
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull(message = "Created on date is missing.")
    @Column(name = "created_on", updatable = false, nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDate updatedAt;
}
