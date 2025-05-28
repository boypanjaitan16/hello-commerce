package com.boy.pjtn.hello.configs;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.boy.pjtn.hello.exceptions.DataNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleProductNotFound(DataNotFoundException ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("timestamp", LocalDateTime.now());
    error.put("status", HttpStatus.NOT_FOUND.value());
    error.put("error", "Not Found");
    error.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  // You can add more handlers here (e.g., MethodArgumentNotValidException)
}
