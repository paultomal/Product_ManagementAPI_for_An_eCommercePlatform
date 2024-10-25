package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventListener {
    @EventListener
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        log.info("Product is created. Name: {}", event.getProduct().getName());
    }

    @EventListener
    public void handleProductStockUpdatedEvent(ProductStockUpdatedEvent event) {
        log.info("Handling Product Stock Updated Event for product: {}, Old Stock: {}, New Stock: {}",
                event.getProduct().getName(), event.getOldStock(), event.getNewStock());
    }
}
