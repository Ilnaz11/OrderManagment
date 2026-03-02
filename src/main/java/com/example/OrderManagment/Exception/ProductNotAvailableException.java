package com.example.OrderManagment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductNotAvailableException extends BusinessException {
    public ProductNotAvailableException(Long productId) {
        super("Product " + productId + " is not available");
    }
}

