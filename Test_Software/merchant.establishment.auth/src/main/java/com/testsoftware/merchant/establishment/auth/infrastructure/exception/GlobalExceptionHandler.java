package com.testsoftware.merchant.establishment.auth.infrastructure.exception;

import com.testsoftware.merchant.establishment.auth.infrastructure.dto.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GeneralResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
            GeneralResponse.error("01", ex.getMessage())
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponse<Object>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(
            GeneralResponse.error("404", ex.getMessage())
        );
    }
}
