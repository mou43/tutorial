package com.ccsw.tutorial.exception;

public class ClientMaxRentalsException extends RuntimeException {
    public ClientMaxRentalsException() {
        super("No puedes alquilar más de 2 juegos en un mismo periodo");
    }
}
