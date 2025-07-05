package com.accenture.franquiciaCore.config;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String,String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(err ->
      errors.put(err.getField(), err.getDefaultMessage())
    );
    return ResponseEntity.badRequest().body(errors);
  }
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String,String>> handleConstraintViolations(ConstraintViolationException ex) {
    Map<String,String> errors = ex.getConstraintViolations().stream()
      .collect(Collectors.toMap(
        cv -> cv.getPropertyPath().toString(),
        ConstraintViolation::getMessage
      ));
    return ResponseEntity.badRequest().body(errors);
  }
  
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex) {
    Map<String,String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    return ResponseEntity.badRequest().body(errors);
  }
} 
