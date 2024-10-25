package com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.event;

import com.example.Product_ManagementAPI_for_An_eCommercePlatform.product.domain.entity.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductStockUpdatedEvent extends ApplicationEvent {
    private final Product product;
    private final int oldStock;
    private final int newStock;

    public ProductStockUpdatedEvent(Product product, int oldStock, int newStock) {
        super(product);
        this.product = product;
        this.oldStock = oldStock;
        this.newStock = newStock;
    }

}
