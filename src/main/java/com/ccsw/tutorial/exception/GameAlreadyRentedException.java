package com.ccsw.tutorial.exception;

public class GameAlreadyRentedException extends RuntimeException {
    public GameAlreadyRentedException() {
        super("El juego ya está alquilado por otro cliente");
    }
}
