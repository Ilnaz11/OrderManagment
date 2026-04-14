package com.example.OrderManagment.Advice;

import com.example.OrderManagment.Exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity.
                badRequest()
                .body(error(400, "BUSINESS_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProduct(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrder(OrderNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleProductNotAvailable(ProductNotAvailableException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error(HttpStatus.CONFLICT.value(), "PRODUCT_NOT_AVAILABLE", ex.getMessage()));
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderStatus(InvalidOrderStatusException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error(HttpStatus.CONFLICT.value(), "INVALID_ORDER_STATUS", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_ERROR", "Unexpected error"));
    }

    private ErrorResponse error(int status, String error, String message) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status);
        response.setError(error);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
