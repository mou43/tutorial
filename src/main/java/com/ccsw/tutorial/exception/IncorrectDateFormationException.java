package com.ccsw.tutorial.exception;

public class IncorrectDateFormationException extends RuntimeException {
    public IncorrectDateFormationException() {
        super("La fecha de devolución no puede ser anterior a la del préstamo");
    }
}
