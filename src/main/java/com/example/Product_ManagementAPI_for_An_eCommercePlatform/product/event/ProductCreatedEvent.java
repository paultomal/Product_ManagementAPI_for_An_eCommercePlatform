package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.event;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductCreatedEvent extends ApplicationEvent {
    private final Product product;

    public ProductCreatedEvent(Product product) {
        super(product);
        this.product = product;
    }
}
