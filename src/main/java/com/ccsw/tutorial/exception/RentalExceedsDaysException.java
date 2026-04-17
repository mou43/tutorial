package com.ccsw.tutorial.exception;

public class RentalExceedsDaysException extends RuntimeException {
    public RentalExceedsDaysException() {
        super("El préstamo de un juego no puede superar los 14 días");
    }
}
