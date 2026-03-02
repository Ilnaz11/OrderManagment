package com.example.OrderManagment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidOrderStatusException extends BusinessException {
    public InvalidOrderStatusException(String from, String to) {
        super("Cannot change order status from " + from + " to " + to);
    }
}
