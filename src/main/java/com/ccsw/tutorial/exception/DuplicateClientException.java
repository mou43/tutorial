package com.ccsw.tutorial.exception;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String name) {
        super("Ya existe un cliente con el nombre: " + name);
    }
}
