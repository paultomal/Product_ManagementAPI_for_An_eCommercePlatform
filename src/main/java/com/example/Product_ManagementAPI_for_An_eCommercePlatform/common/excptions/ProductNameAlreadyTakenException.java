package com.example.Product_ManagementAPI_for_An_eCommercePlatform.common.excptions;

public class ProductNameAlreadyTakenException extends RuntimeException{
    public ProductNameAlreadyTakenException(String message) {
        super(message);
    }
}
