package com.microservice.product.exception.advices;

import com.microservice.product.exception.ExceptionDetail;
import com.microservice.product.exception.ProductException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> manageProductException(ProductException ex){
        var error = new ExceptionDetail();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_GATEWAY);
        error.setTimestamp( new Date());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
