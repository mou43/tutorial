package com.ccsw.tutorial.common;

import com.ccsw.tutorial.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateClientException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateClientException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(IncorrectDateFormationException.class)
    public ResponseEntity<Map<String, String>> incorrectDate(IncorrectDateFormationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RentalExceedsDaysException.class)
    public ResponseEntity<Map<String, String>> rentalExceedsDays(RentalExceedsDaysException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(GameAlreadyRentedException.class)
    public ResponseEntity<Map<String, String>> gameAlreadyRented(GameAlreadyRentedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ClientMaxRentalsException.class)
    public ResponseEntity<Map<String, String>> clientMaxRentals(ClientMaxRentalsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
}
