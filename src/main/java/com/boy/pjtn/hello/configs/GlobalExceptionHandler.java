package com.boy.pjtn.hello.configs;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.boy.pjtn.hello.exceptions.DataNotFoundException;
import com.boy.pjtn.hello.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleDataNotFound(DataNotFoundException ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleNoHandlerFound(NoHandlerFoundException ex) {
    String message = "The route " + ex.getRequestURL() + " was not found.";
    return buildErrorResponse(HttpStatus.NOT_FOUND, message);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Object>> handleValidationErrors(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
    return buildErrorResponse(HttpStatus.BAD_REQUEST, errors.toString());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<Object>> handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException ex) {
    String message = "HTTP method " + ex.getMethod() + " not supported for this endpoint.";
    return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, message);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
  }

  private ResponseEntity<ApiResponse<Object>> buildErrorResponse(HttpStatus status,
      String message) {
    return ResponseEntity.status(status).body(ApiResponse.builder().status(status.value())
        .error(message).data(null).timestamp(LocalDateTime.now()).build());
  }
}
